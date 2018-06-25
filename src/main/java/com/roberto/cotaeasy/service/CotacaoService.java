package com.roberto.cotaeasy.service;

import com.roberto.cotaeasy.domain.entities.Cotacao;
import com.roberto.cotaeasy.domain.entities.CotacaoFornecedor;
import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.domain.enums.EPerfil;
import com.roberto.cotaeasy.domain.models.NovaCotacaoModel;
import com.roberto.cotaeasy.repository.CotacaoFornecedorRepository;
import com.roberto.cotaeasy.repository.CotacaoRepository;

import com.roberto.cotaeasy.repository.UsuarioRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.LinkedList;

@Service
@Transactional
public class CotacaoService {
    private final Logger log = LoggerFactory.getLogger(CotacaoService.class);
    private UsuarioRepository usuarioRepository;
    private CotacaoRepository cotacaoRepository;
    private CotacaoFornecedorRepository cotacaoFornecedorRepository;

    @Autowired
    public CotacaoService(UsuarioRepository usuarioRepository,
                          CotacaoRepository cotacaoRepository,
                          CotacaoFornecedorRepository cotacaoFornecedorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.cotacaoFornecedorRepository = cotacaoFornecedorRepository;
    }

    public NovaCotacaoModel novaCotacao(NovaCotacaoModel novaCotacaoModel) throws Exception {
        try {
            Cotacao cotacao = cotacaoRepository.save(novaCotacaoModel.getCotacao());
            for (CotacaoFornecedor cotacaoFornecedor : novaCotacaoModel.getFornecedores()) {
                Usuario fornecedor = usuarioRepository.findOne(cotacaoFornecedor.getId());
                if (fornecedor != null && fornecedor.getPerfil() != EPerfil.FORNECEDOR)
                    throw new Exception("O usuário precisa ser um fornecedor.");

                cotacaoFornecedor.setFornecedor(fornecedor);
                cotacaoFornecedor.setCotacao(cotacao);
                cotacaoFornecedorRepository.save(cotacaoFornecedor);
            }
            return novaCotacaoModel;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public LinkedList<Cotacao> getCotacoesDisponiveisFornecedor(long idFornecedor) throws Exception {
        try {
            LinkedList<Cotacao> listaCotacoes = new LinkedList<>();
            LinkedList<CotacaoFornecedor> cotacoesFornecedor = cotacaoFornecedorRepository.getAllByFornecedorId(idFornecedor);
            if (cotacoesFornecedor == null)
                throw new Exception("Nenhuma cotação foi encontrada.");

            for (CotacaoFornecedor cotacaoFornecedor : cotacoesFornecedor) {
                Cotacao cotacao = cotacaoRepository.getOne(cotacaoFornecedor.getCotacao().getId());
                if (cotacao != null &&
                    cotacao.getDataInicio().before(DateTime.now().toDate()) &&
                    cotacao.getDataFinal().after(DateTime.now().toDate())) {
                    listaCotacoes.push(cotacao);
                }
            }
            return listaCotacoes;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public LinkedList<Cotacao> getCotacoesDisponiveisUsuario(long idUsuario) throws Exception {
        try {
            return cotacaoRepository.getAllByUsuarioId(idUsuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
