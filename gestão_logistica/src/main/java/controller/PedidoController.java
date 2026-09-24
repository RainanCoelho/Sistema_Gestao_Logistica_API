package controller;

import DTO.Pedido.PedidoRequestDTO;
import DTO.Pedido.PedidoResponseDTO;
import entity.Pedido;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PedidoResponseDTO> salvar(
            @RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO response = pedidoService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {

        List<PedidoResponseDTO> response = pedidoService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        PedidoResponseDTO response = pedidoService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO response = pedidoService.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        pedidoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
