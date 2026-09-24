package controller;

import DTO.Produto.ProdutoRequestDTO;
import DTO.Produto.ProdutoResponseDTO;
import entity.Produto;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/vendedor/{idVendedor}")
    public ResponseEntity<ProdutoResponseDTO> salvar(
            @PathVariable("idVendedor") Long idVendedor,
            @RequestBody ProdutoRequestDTO request) {

        ProdutoResponseDTO response =
                produtoService.salvar(request, idVendedor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {

        List<ProdutoResponseDTO> response = produtoService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        ProdutoResponseDTO response = produtoService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody ProdutoRequestDTO request) {

        ProdutoResponseDTO response = produtoService.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
