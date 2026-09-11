package service;

import entity.Entrega;
import entity.Produto;
import entity.Usuario;
import entity.Veiculo;
import org.springframework.stereotype.Service;
import repository.EntregaRepository;
import repository.ProdutoRepository;
import repository.UsuarioRepository;
import repository.VeiculoRepository;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final VeiculoRepository veiculoRepository;

    public EntregaService(
            EntregaRepository entregaRepository,
            UsuarioRepository usuarioRepository,
            ProdutoRepository produtoRepository,
            VeiculoRepository veiculoRepository) {

        this.entregaRepository = entregaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public Entrega salvar(
            Entrega entrega,
            Long motoristaId,
            Long clienteId,
            Long produtoId,
            Long veiculoId) {

        Usuario motorista = usuarioRepository.findById(motoristaId)
                .orElseThrow(() ->
                        new RuntimeException("Motorista não encontrado"));

        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado"));

        entrega.setMotorista(motorista);
        entrega.setCliente(cliente);
        entrega.setProduto(produto);
        entrega.setVeiculo(veiculo);

        return entregaRepository.save(entrega);
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega buscarPorId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Entrega não encontrada"));
    }

    public Entrega atualizarStatus(
            Long id,
            String novoStatus) {

        Entrega entrega = buscarPorId(id);

        entrega.setStatusEntrega(novoStatus);

        return entregaRepository.save(entrega);
    }
}
