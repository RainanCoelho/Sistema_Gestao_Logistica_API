package controller;

import DTO.Entrega.EntregaRequestDTO;
import DTO.Entrega.EntregaResponseDTO;
import entity.Entrega;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EntregaResponseDTO> salvar(
            @RequestBody EntregaRequestDTO request) {

        EntregaResponseDTO response = entregaService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EntregaResponseDTO>> listar() {

        List<EntregaResponseDTO> response = entregaService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        EntregaResponseDTO response = entregaService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody EntregaRequestDTO request) {

        EntregaResponseDTO response = entregaService.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        entregaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
