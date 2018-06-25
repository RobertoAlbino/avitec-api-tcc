package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;
import com.roberto.cotaeasy.domain.entities.Cotacao;
import com.roberto.cotaeasy.domain.models.NovaCotacaoModel;
import com.roberto.cotaeasy.service.CotacaoService;

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
            cotacaoService.novaCotacao(novaCotacaoModel);
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
            LinkedList<Cotacao> cotacoes = cotacaoService.getCotacoesDisponiveisUsuario(idUsuario);
            return new RetornoBaseModel<LinkedList<Cotacao>>(true, "Cotações do usuário.", cotacoes);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<Cotacao>>(false, ex.getMessage(), null);
        }
    }
}
