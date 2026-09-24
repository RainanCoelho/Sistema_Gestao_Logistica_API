package controller;

import DTO.ItemProduto.ItemProdutoResponseDTO;
import entity.ItemProduto;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/produto/{idProduto}")
    public ResponseEntity<ItemProdutoResponseDTO> salvar(
            @PathVariable("idProduto") Long idProduto) {

        ItemProdutoResponseDTO response =
                itemProdutoService.salvar(idProduto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemProdutoResponseDTO>> listar() {

        List<ItemProdutoResponseDTO> response =
                itemProdutoService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemProdutoResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        ItemProdutoResponseDTO response =
                itemProdutoService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        itemProdutoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}