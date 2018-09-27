package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Cotacao;
import com.roberto.avitec.domain.entities.CotacaoLance;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.enums.ETipoEmail;
import com.roberto.avitec.domain.models.BuscarLancesModel;
import com.roberto.avitec.domain.models.NovaCotacaoModel;
import com.roberto.avitec.domain.models.NovoLanceModel;
import com.roberto.avitec.repository.CotacaoLanceRepository;
import com.roberto.avitec.repository.CotacaoRepository;

import com.roberto.avitec.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;

import static org.springframework.core.OrderComparator.sort;

@Service
@Transactional
public class LanceService {
    private final Logger log = LoggerFactory.getLogger(LanceService.class);
    LogEmailService logEmailService;
    private UsuarioRepository usuarioRepository;
    private CotacaoRepository cotacaoRepository;
    private CotacaoLanceRepository cotacaoLanceRepository;

    @Autowired
    public LanceService(LogEmailService logEmailService,
                        UsuarioRepository usuarioRepository,
                        CotacaoRepository cotacaoRepository,
                        CotacaoLanceRepository cotacaoLanceRepository) {
        this.logEmailService = logEmailService;
        this.usuarioRepository = usuarioRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.cotacaoLanceRepository = cotacaoLanceRepository;
    }

    public void novoLance(NovoLanceModel novoLanceModel) throws Exception {
        try {
            Usuario fornecedor = usuarioRepository.findOne(novoLanceModel.getIdFornecedor());
            boolean existeLanceFornecedor = cotacaoLanceRepository.
                    existsByFornecedorEmailAndCotacaoProdutoId(fornecedor.getEmail(),
                                                               novoLanceModel.getCotacao().getProduto().getId());
            if (existeLanceFornecedor)
                throw new Exception("É permitido apenas um lance por produto.");

            CotacaoLance cotacaoLance = new CotacaoLance();
            cotacaoLance.setLance(novoLanceModel.getLance());
            cotacaoLance.setCotacao(novoLanceModel.getCotacao());
            cotacaoLance.setFornecedor(fornecedor);
            cotacaoLanceRepository.save(cotacaoLance);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public LinkedList<CotacaoLance> getTodosLanceProduto(BuscarLancesModel buscarLancesModel) throws Exception {
        try {
            return  cotacaoLanceRepository.getAllByCotacaoUsuarioIdAndCotacaoProdutoId(buscarLancesModel.getIdUsuario(),
                                                                                       buscarLancesModel.getIdProduto());
        } catch (Exception ex) {
            throw  new Exception(ex.getMessage());
        }
    }

    public void EnviarEmailAvisandoCompradorNovoLance(NovoLanceModel novoLanceModel) {
        Usuario fornecedor = usuarioRepository.findOne(novoLanceModel.getIdFornecedor());
        if (fornecedor == null)
            return;

        String assunto = "Novo lance recebido - " + novoLanceModel.getCotacao().getProduto().getNome();
        String corpoEmail =   "O produto: " + novoLanceModel.getCotacao().getProduto().getNome() +
                         ", acabou de receber um lance no valor de R$: " + novoLanceModel.getLance() +
                         ", o lance foi realizado pelo fornecedor: " + fornecedor.getNome() +
                         ", email do fornecedor: " + fornecedor.getEmail();

        Runnable emailRunnable = new EmailService(novoLanceModel.getCotacao().getUsuario().getEmail(), assunto, corpoEmail , ETipoEmail.NOVO_LANCE_RECEBIDO);
        new Thread(emailRunnable).start();
        logEmailService.gravarLogEnvioEmail(assunto, corpoEmail,novoLanceModel.getCotacao().getUsuario().getEmail() , ETipoEmail.NOVO_LANCE_RECEBIDO);
    }
}
