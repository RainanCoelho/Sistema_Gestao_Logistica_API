package controller;

import DTO.Pagamento.PagamentoRequestDTO;
import DTO.Pagamento.PagamentoResponseDTO;
import entity.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PagamentoService;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> salvar(
            @RequestBody PagamentoRequestDTO request) {

        PagamentoResponseDTO response = pagamentoService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listar() {

        List<PagamentoResponseDTO> response = pagamentoService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        PagamentoResponseDTO response = pagamentoService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody PagamentoRequestDTO request) {

        PagamentoResponseDTO response = pagamentoService.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        pagamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
