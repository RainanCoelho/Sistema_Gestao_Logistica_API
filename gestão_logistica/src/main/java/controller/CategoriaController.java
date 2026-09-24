package controller;

import DTO.Categoria.CategoriaRequestDTO;
import DTO.Categoria.CategoriaResponseDTO;
import entity.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(
            @RequestBody CategoriaRequestDTO request) {

        CategoriaResponseDTO response = categoriaService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {

        List<CategoriaResponseDTO> response = categoriaService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        CategoriaResponseDTO response = categoriaService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody CategoriaRequestDTO request) {

        CategoriaResponseDTO response = categoriaService.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
