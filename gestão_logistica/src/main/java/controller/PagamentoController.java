package controller;

import entity.Pagamento;
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
    public ResponseEntity<Pagamento> salvar(
            @RequestBody Pagamento pagamento) {

        return ResponseEntity.ok(
                pagamentoService.salvar(pagamento)
        );
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listar() {

        return ResponseEntity.ok(
                pagamentoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pagamentoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(
            @PathVariable Long id,
            @RequestBody Pagamento pagamento) {

        return ResponseEntity.ok(
                pagamentoService.atualizar(id, pagamento)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        pagamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
