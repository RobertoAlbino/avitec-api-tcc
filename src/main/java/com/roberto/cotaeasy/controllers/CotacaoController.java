package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;
import com.roberto.cotaeasy.domain.entities.Cotacao;
import com.roberto.cotaeasy.domain.entities.CotacaoLance;
import com.roberto.cotaeasy.domain.models.NovaCotacaoModel;
import com.roberto.cotaeasy.service.CotacaoService;
import com.roberto.cotaeasy.utils.DateUtils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {
    private final Logger log = LoggerFactory.getLogger(CotacaoController.class);
    private RetornoBaseModel objetoRetorno;
    private CotacaoService cotacaoService;

    @Autowired
    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @PostMapping(value = "/novaCotacao", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel novaCotacao(@RequestBody NovaCotacaoModel novaCotacaoModel) throws Exception {
        try {
            novaCotacaoModel.getCotacao().setDataInicio(DateUtils.removerHorasData(new DateTime(novaCotacaoModel.getCotacao().getDataInicio()).plusDays(1).toDate()));
            novaCotacaoModel.getCotacao().setDataFinal(DateUtils.removerHorasData(new DateTime(novaCotacaoModel.getCotacao().getDataFinal()).plusDays(1).toDate()));
            cotacaoService.novaCotacao(novaCotacaoModel);
            cotacaoService.EnviarEmailAvisandoFornecedoresNovaCotacao(novaCotacaoModel);
            return new RetornoBaseModel<NovaCotacaoModel>(true, "Cotação iniciada.", novaCotacaoModel);
        } catch (Exception ex) {
            return new RetornoBaseModel<NovaCotacaoModel>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/getCotacoesDisponiveisFornecedor", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel getCotacoesDisponiveisFornecedor(@RequestBody long idFornecedor) throws Exception {
        try {
            LinkedList<Cotacao> cotacoesDisponiveis = cotacaoService.getCotacoesDisponiveisFornecedor(idFornecedor);
            return new RetornoBaseModel<LinkedList<Cotacao>>(true, "Cotação disponíveis para o fornecedor.", cotacoesDisponiveis);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<Cotacao>>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/getCotacoesUsuario", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel getCotacoesUsuario(@RequestBody long idUsuario) throws Exception {
        try {
            LinkedList<Cotacao> cotacoesWithLances = new LinkedList<>();
            LinkedList<CotacaoLance> cotacoes = cotacaoService.getCotacoesDisponiveisUsuario(idUsuario);
            for (CotacaoLance cotacaoLance : cotacoes) {
                boolean produtoExistente = false;
                for (Cotacao cotacao : cotacoesWithLances) {
                    if (cotacao.getId() == cotacaoLance.getCotacao().getId())
                        produtoExistente = true;
                }
                if (!produtoExistente)
                    cotacoesWithLances.push(cotacaoLance.getCotacao());
            }
            return new RetornoBaseModel<LinkedList<Cotacao>>(true, "Cotações do usuário.", cotacoesWithLances);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<Cotacao>>(false, ex.getMessage(), null);
        }
    }
}
