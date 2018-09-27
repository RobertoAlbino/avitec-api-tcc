package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Produto;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.enums.EPerfil;
import com.roberto.avitec.repository.CotacaoFornecedorRepository;
import com.roberto.avitec.repository.CotacaoLanceRepository;
import com.roberto.avitec.repository.CotacaoRepository;
import com.roberto.avitec.repository.ProdutoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Service
@Transactional
public class ProdutoService {
    private final Logger log = LoggerFactory.getLogger(ProdutoService.class);
    private ProdutoRepository produtoRepository;
    private CotacaoRepository cotacaoRepository;
    private CotacaoFornecedorRepository cotacaoFornecedorRepository;
    private CotacaoLanceRepository cotacaoLanceRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository,
                          CotacaoRepository cotacaoRepository,
                          CotacaoFornecedorRepository cotacaoFornecedorRepository,
                          CotacaoLanceRepository cotacaoLanceRepository) {
        this.produtoRepository = produtoRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.cotacaoFornecedorRepository = cotacaoFornecedorRepository;
        this.cotacaoLanceRepository = cotacaoLanceRepository;
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(long idProduto) throws Exception {
        try {
            cotacaoLanceRepository.deleteByCotacaoProdutoId(idProduto);
            cotacaoFornecedorRepository.deleteByCotacaoProdutoId(idProduto);
            cotacaoRepository.deleteByProdutoId(idProduto);
            produtoRepository.delete(idProduto);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public LinkedList<Produto> getAllByUsuarioId(Usuario usuario) throws Exception {
        if (usuario.getPerfil() != EPerfil.USUARIO)
            throw new Exception("Usuário não habilitado para consultar lista de produtos.");

        return produtoRepository.getAllByUsuarioId(usuario.getId());
    }
}
