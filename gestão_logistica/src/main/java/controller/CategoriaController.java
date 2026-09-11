package controller;

import entity.Categoria;
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
    public ResponseEntity<Categoria> salvar(
            @RequestBody Categoria categoria) {

        return ResponseEntity.ok(
                categoriaService.salvar(categoria)
        );
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {

        return ResponseEntity.ok(
                categoriaService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoriaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        return ResponseEntity.ok(
                categoriaService.atualizar(id, categoria)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
