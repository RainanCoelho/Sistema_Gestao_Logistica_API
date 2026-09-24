package controller;

import DTO.Veiculo.VeiculoRequestDTO;
import DTO.Veiculo.VeiculoResponseDTO;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<VeiculoResponseDTO> salvar(@RequestBody VeiculoRequestDTO request){
        VeiculoResponseDTO response = veiculoService.salvar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {

        List<VeiculoResponseDTO> response = veiculoService.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(
            @PathVariable("id") Long id) {

        VeiculoResponseDTO responseDTO = veiculoService.buscarPorId(id);

        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @RequestBody VeiculoRequestDTO request) {

        VeiculoResponseDTO responseDTO = veiculoService.atualizar(id, request);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        veiculoService.desativar(id);

        return ResponseEntity.noContent().build();
    }
}
