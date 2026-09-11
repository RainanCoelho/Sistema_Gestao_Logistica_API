package controller;

import entity.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(
            @RequestBody Produto produto,
            @RequestParam Long categoriaId) {

        return ResponseEntity.ok(
                produtoService.salvar(produto, categoriaId)
        );
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {

        return ResponseEntity.ok(
                produtoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                produtoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto,
            @RequestParam Long categoriaId) {

        return ResponseEntity.ok(
                produtoService.atualizar(
                        id,
                        produto,
                        categoriaId
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
