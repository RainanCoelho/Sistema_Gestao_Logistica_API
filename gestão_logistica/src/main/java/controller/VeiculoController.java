package controller;

import entity.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvar(
            @RequestBody Veiculo veiculo) {

        return ResponseEntity.ok(
                veiculoService.salvar(veiculo)
        );
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {

        return ResponseEntity.ok(
                veiculoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                veiculoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(
            @PathVariable Long id,
            @RequestBody Veiculo veiculo) {

        return ResponseEntity.ok(
                veiculoService.atualizar(id, veiculo)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        veiculoService.desativar(id);

        return ResponseEntity.noContent().build();
    }
}
