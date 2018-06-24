package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;
import com.roberto.cotaeasy.domain.entities.Produto;
import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.service.ProdutoService;

import com.roberto.cotaeasy.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final Logger log = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, UsuarioService usuarioService) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/cadastrarProduto", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel cadastrarProduto(@RequestBody Produto produto) throws Exception {
        try {
            produtoService.cadastrarProduto(produto);
            return new RetornoBaseModel<Produto>(true, "Produto cadastrado com sucesso.", produto);
        } catch (Exception ex) {
            return new RetornoBaseModel<Produto>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/deleteById", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel deleteById(@RequestBody long idProduto) throws Exception {
        try {
            produtoService.deleteById(idProduto);
            return new RetornoBaseModel<LinkedList<Produto>>(true, "Produto removido com sucesso.", null);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<Produto>>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/getAllByUsuarioId", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel getAllByUsuarioId(@RequestBody long idUsuario) throws Exception {
        try {
            Usuario usuarioEntidade = usuarioService.consultarUsuario(idUsuario);
            if (usuarioEntidade == null)
                return new RetornoBaseModel<LinkedList<Produto>>(false, "Usuário não encontrado.", null);

            LinkedList<Produto> listaProdutos = produtoService.getAllByUsuarioId(usuarioEntidade);
            return new RetornoBaseModel<LinkedList<Produto>>(true, "Lista de produtos.", listaProdutos);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<Produto>>(false, ex.getMessage(), null);
        }
    }
}
