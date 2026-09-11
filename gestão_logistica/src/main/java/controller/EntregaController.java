package controller;

import entity.Entrega;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EntregaService;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public ResponseEntity<Entrega> salvar(
            @RequestBody Entrega entrega,
            @RequestParam Long motoristaId,
            @RequestParam Long clienteId,
            @RequestParam Long produtoId,
            @RequestParam Long veiculoId) {

        return ResponseEntity.ok(
                entregaService.salvar(
                        entrega,
                        motoristaId,
                        clienteId,
                        produtoId,
                        veiculoId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> listar() {

        return ResponseEntity.ok(
                entregaService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                entregaService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Entrega> atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                entregaService.atualizarStatus(id, status)
        );
    }
}
