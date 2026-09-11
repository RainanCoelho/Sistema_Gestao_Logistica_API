package service;

import entity.Pedido;
import org.springframework.stereotype.Service;
import repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));
    }

    public Pedido atualizar(
            Long id,
            Pedido pedidoAtualizado) {

        Pedido pedido = buscarPorId(id);

        pedido.setDataPedido(
                pedidoAtualizado.getDataPedido()
        );

        pedido.setStatusPedido(
                pedidoAtualizado.getStatusPedido()
        );

        pedido.setValorTotal(
                pedidoAtualizado.getValorTotal()
        );

        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {

        Pedido pedido = buscarPorId(id);

        pedidoRepository.delete(pedido);
    }
}
