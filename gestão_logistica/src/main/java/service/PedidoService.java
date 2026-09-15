package service;

import entity.Pedido;
import org.springframework.stereotype.Service;
import repository.PedidoRepository;
import DTO.Pedido.PedidoRequestDTO;
import DTO.Pedido.PedidoResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    private PedidoResponseDTO converterParaResponse(Pedido pedido){

        PedidoResponseDTO response = new PedidoResponseDTO();

        response.setIdPedido(pedido.getIdPedido());
        response.setDataPedido(pedido.getDataPedido());
        response.setStatusPedido(pedido.getStatusPedido());
        response.setValorTotal(pedido.getValorTotal());

        return response;
    }


    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {

        Pedido pedido = new Pedido();

        pedido.setDataPedido(pedidoRequestDTO.getDataPedido());
        pedido.setStatusPedido(pedidoRequestDTO.getStatusPedido());
        pedido.setValorTotal(pedidoRequestDTO.getValorTotal());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return converterParaResponse(pedidoSalvo);

    }

    public List<PedidoResponseDTO> listar() {

            List<Pedido> pedidos = pedidoRepository.findAll();

            List<PedidoResponseDTO> responses = new ArrayList<>();

            for (Pedido pedido : pedidos) {
                PedidoResponseDTO response = converterParaResponse(pedido);
                responses.add(response);
            }

            return responses;
    }

    public PedidoResponseDTO buscarPorId(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));

        return converterParaResponse(pedido);
    }

    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO request) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));

        pedido.setDataPedido(request.getDataPedido());
        pedido.setStatusPedido(request.getStatusPedido());
        pedido.setValorTotal(request.getValorTotal());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return converterParaResponse(pedidoAtualizado);
    }

    public void excluir(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));

        pedidoRepository.delete(pedido);
    }
}
