package controller;

import entity.ItemProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ItemProdutoService;

import java.util.List;

@RestController
@RequestMapping("/itens-produto")
public class ItemProdutoController {

    private final ItemProdutoService itemProdutoService;

    public ItemProdutoController(ItemProdutoService itemProdutoService) {
        this.itemProdutoService = itemProdutoService;
    }

    @PostMapping
    public ResponseEntity<ItemProduto> salvar(
            @RequestBody ItemProduto itemProduto,
            @RequestParam Long produtoId) {

        return ResponseEntity.ok(
                itemProdutoService.salvar(
                        itemProduto,
                        produtoId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ItemProduto>> listar() {

        return ResponseEntity.ok(
                itemProdutoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemProduto> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                itemProdutoService.buscarPorId(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        itemProdutoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}