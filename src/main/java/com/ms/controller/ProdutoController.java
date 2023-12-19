package com.ms.controller;

import com.ms.exception.ProdutoNotFoundException;
import com.ms.model.Produto;
import com.ms.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

    @GetMapping
    public List<Produto> getAll() {
        List<Produto> todos = produtoService.getAll();
        LOGGER.info("GET ALL: " + todos);
        return todos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable long id) {
        try {
            Produto produto = produtoService.getById(id);
            LOGGER.info("GET BY ID: " + produto);
            return ResponseEntity.ok(produto);

        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        produtoService.deleteById(id);
        LOGGER.info("DELETE");
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Produto produto1 = produtoService.create(produto);
        LOGGER.info("CREATE: " + produto1);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto1);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Produto produto) {
        produtoService.update(id, produto);
        LOGGER.info("UPDATE: " + id);
    }

}
