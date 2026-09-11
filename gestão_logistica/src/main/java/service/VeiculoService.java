package service;

import entity.Veiculo;
import org.springframework.stereotype.Service;
import repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado"));
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {

        Veiculo veiculo = buscarPorId(id);

        veiculo.setPlaca(veiculoAtualizado.getPlaca());
        veiculo.setMarca(veiculoAtualizado.getMarca());
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setAno(veiculoAtualizado.getAno());
        veiculo.setTipoVeiculo(veiculoAtualizado.getTipoVeiculo());
        veiculo.setCor(veiculoAtualizado.getCor());
        veiculo.setAtivo(veiculoAtualizado.getAtivo());

        return veiculoRepository.save(veiculo);
    }

    public void desativar(Long id) {

        Veiculo veiculo = buscarPorId(id);

        veiculo.setAtivo(false);

        veiculoRepository.save(veiculo);
    }

}
