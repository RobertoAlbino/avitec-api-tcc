package com.roberto.cotaeasy.service;

import com.roberto.cotaeasy.domain.entities.Cotacao;
import com.roberto.cotaeasy.domain.entities.CotacaoLance;
import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.domain.models.BuscarLancesModel;
import com.roberto.cotaeasy.domain.models.NovaCotacaoModel;
import com.roberto.cotaeasy.domain.models.NovoLanceModel;
import com.roberto.cotaeasy.repository.CotacaoLanceRepository;
import com.roberto.cotaeasy.repository.CotacaoRepository;

import com.roberto.cotaeasy.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    private CotacaoRepository cotacaoRepository;
    private CotacaoLanceRepository cotacaoLanceRepository;

    @Autowired
    public LanceService(UsuarioRepository usuarioRepository,
                        CotacaoRepository cotacaoRepository,
                        CotacaoLanceRepository cotacaoLanceRepository) {
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
}
