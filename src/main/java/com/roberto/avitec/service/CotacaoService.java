package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Cotacao;
import com.roberto.avitec.domain.entities.CotacaoFornecedor;
import com.roberto.avitec.domain.entities.CotacaoLance;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.enums.EPerfil;
import com.roberto.avitec.domain.enums.ETipoEmail;
import com.roberto.avitec.domain.models.NovaCotacaoModel;
import com.roberto.avitec.repository.CotacaoFornecedorRepository;
import com.roberto.avitec.repository.CotacaoLanceRepository;
import com.roberto.avitec.repository.CotacaoRepository;

import com.roberto.avitec.repository.UsuarioRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.LinkedList;

@Service
@Transactional
public class CotacaoService {
    private final Logger log = LoggerFactory.getLogger(CotacaoService.class);
    private LogEmailService logEmailService;
    private UsuarioRepository usuarioRepository;
    private CotacaoRepository cotacaoRepository;
    private CotacaoFornecedorRepository cotacaoFornecedorRepository;
    private CotacaoLanceRepository cotacaoLanceRepository;

    @Autowired
    public CotacaoService(LogEmailService logEmailService,
                          UsuarioRepository usuarioRepository,
                          CotacaoRepository cotacaoRepository,
                          CotacaoFornecedorRepository cotacaoFornecedorRepository,
                          CotacaoLanceRepository cotacaoLanceRepository) {
        this.logEmailService = logEmailService;
        this.usuarioRepository = usuarioRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.cotacaoFornecedorRepository = cotacaoFornecedorRepository;
        this.cotacaoLanceRepository = cotacaoLanceRepository;
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
            if (cotacoesFornecedor.size() == 0)
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

    public LinkedList<CotacaoLance> getCotacoesDisponiveisUsuario(long idUsuario) throws Exception {
        try {
            return cotacaoLanceRepository.getAllByCotacaoUsuarioId(idUsuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void EnviarEmailAvisandoFornecedoresNovaCotacao(NovaCotacaoModel novaCotacaoModel) {
        String fornecedoresDestinatatios = "";
        for (CotacaoFornecedor cotacaoFornecedor : novaCotacaoModel.getFornecedores()) {
            Usuario fornecedor = usuarioRepository.findOne(cotacaoFornecedor.getFornecedor().getId());
            if (fornecedor == null)
                continue;

            fornecedoresDestinatatios+= fornecedor.getEmail() + ",";
        }

        String assunto = "Nova cotação iniciada";
        String corpoEmail = "Uma nova cotação foi iniciada para o produto: " +
                novaCotacaoModel.getCotacao().getProduto().getNome() + ", com data de ínicio: "
                + DateFormat.getDateInstance().format(novaCotacaoModel.getCotacao().getDataInicio()) + " e data de término: "
                + DateFormat.getDateInstance().format(novaCotacaoModel.getCotacao().getDataFinal()) +
                ", Comprador: " + novaCotacaoModel.getCotacao().getUsuario().getNome();
        Runnable emailRunnable = new EmailService(fornecedoresDestinatatios, assunto, corpoEmail , ETipoEmail.NOVA_COTACAO_INICIADA);
        new Thread(emailRunnable).start();
        logEmailService.gravarLogEnvioEmail(assunto, corpoEmail, fornecedoresDestinatatios, ETipoEmail.NOVA_COTACAO_INICIADA);
    }
}
