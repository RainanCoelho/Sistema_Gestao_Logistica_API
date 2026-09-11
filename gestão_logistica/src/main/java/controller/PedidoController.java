package controller;

import entity.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(
            @RequestBody Pedido pedido) {

        return ResponseEntity.ok(
                pedidoService.salvar(pedido)
        );
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {

        return ResponseEntity.ok(
                pedidoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        return ResponseEntity.ok(
                pedidoService.atualizar(id, pedido)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        pedidoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
