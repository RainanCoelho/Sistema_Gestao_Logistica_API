package service;

import DTO.Entrega.EntregaRequestDTO;
import DTO.Entrega.EntregaResponse;
import entity.Entrega;
import entity.Produto;
import entity.Usuario;
import entity.Veiculo;
import org.springframework.stereotype.Service;
import repository.EntregaRepository;
import repository.ProdutoRepository;
import repository.UsuarioRepository;
import repository.VeiculoRepository;

import java.util.ArrayList;
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

        public EntregaResponse salvar(EntregaRequestDTO request) {

            Usuario motorista = usuarioRepository
                    .findById(request.getIdMotorista())
                    .orElseThrow(() ->
                            new RuntimeException("Motorista não encontrado"));

            Usuario cliente = usuarioRepository
                    .findById(request.getIdCliente())
                    .orElseThrow(() ->
                            new RuntimeException("Cliente não encontrado"));

            Produto produto = produtoRepository
                    .findById(request.getIdProduto())
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado"));

            Veiculo veiculo = veiculoRepository
                    .findById(request.getIdVeiculo())
                    .orElseThrow(() ->
                            new RuntimeException("Veículo não encontrado"));

            Entrega entrega = new Entrega();

            entrega.setMotorista(motorista);
            entrega.setCliente(cliente);
            entrega.setProduto(produto);
            entrega.setVeiculo(veiculo);

            entrega.setStatusEntrega("PENDENTE");

            Entrega entregaSalva = entregaRepository.save(entrega);

            return converterParaResponse(entregaSalva);
        }

        public List<EntregaResponse> listar() {

            List<Entrega> entregas = entregaRepository.findAll();

            List<EntregaResponse> responses = new ArrayList<>();

            for (Entrega entrega : entregas) {
                EntregaResponse response = converterParaResponse(entrega);
                responses.add(response);
            }

            return responses;
        }

        public EntregaResponse buscarPorId(Long id) {

            Entrega entrega = buscarEntidadePorId(id);

            return converterParaResponse(entrega);
        }

        public EntregaResponse atualizarStatus(Long id, String novoStatus) {

            Entrega entrega = buscarEntidadePorId(id);

            entrega.setStatusEntrega(novoStatus);

            Entrega entregaAtualizada = entregaRepository.save(entrega);

            return converterParaResponse(entregaAtualizada);
        }

        private Entrega buscarEntidadePorId(Long id) {

            return entregaRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Entrega não encontrada"));
        }

        private EntregaResponse converterParaResponse(Entrega entrega) {

            EntregaResponse response = new EntregaResponse();

            response.setIdEntrega(entrega.getIdEntrega());
            response.setDataHoraPrevista(entrega.getDataHoraPrevista());
            response.setDataHoraEntrega(entrega.getDataHoraEntrega());
            response.setStatusEntrega(entrega.getStatusEntrega());

            Usuario motorista = entrega.getMotorista();

            if (motorista != null) {
                response.setIdMotorista(motorista.getIdUsuario());
                response.setNomeMotorista(motorista.getNomeUsuario());
            }

            Usuario cliente = entrega.getCliente();

            if (cliente != null) {
                response.setIdCliente(cliente.getIdUsuario());
                response.setNomeCliente(cliente.getNomeUsuario());
            }

            Produto produto = entrega.getProduto();

            if (produto != null) {
                response.setIdProduto(produto.getIdProduto());
                response.setNomeProduto(produto.getNomeProduto());
                response.setCodigoProduto(produto.getCodigoProduto());
            }

            Veiculo veiculo = entrega.getVeiculo();

            if (veiculo != null) {
                response.setIdVeiculo(veiculo.getIdVeiculo());
                response.setPlacaVeiculo(veiculo.getPlaca());
            }

            return response;
        }
}
