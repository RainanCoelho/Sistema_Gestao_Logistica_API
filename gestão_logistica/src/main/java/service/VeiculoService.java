package service;

import DTO.Veiculo.VeiculoRequestDTO;
import DTO.Veiculo.VeiculoResponseDTO;
import entity.Veiculo;
import org.springframework.stereotype.Service;
import repository.VeiculoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoResponseDTO salvar(VeiculoRequestDTO request) {
        Veiculo veiculo = new Veiculo();
//Vai nada mais que dizer os valores que o susário vai preencher quando ele for cadastrar um veículo
        veiculo.setModelo(request.getModelo());
        veiculo.setMarca(request.getMarca());
        veiculo.setPlaca(request.getPlaca());
        veiculo.setAno(request.getAno());
        veiculo.setCor(request.getCor());
        veiculo.setTipoVeiculo(request.getTipoVeiculo());
        veiculo.setAtivo(request.getAtivo());

        veiculoRepository.save(veiculo);

        VeiculoResponseDTO response = new VeiculoResponseDTO();
//Cada comando desse com response vai nada mais que pegar a informação salva e colocar o objeto no response,
// depois ele vai devolver ao controller que vai enviar ao usuário em formato JSON
        response.setIdVeiculo(veiculo.getIdVeiculo());
        response.setModelo(veiculo.getModelo());
        response.setMarca(veiculo.getMarca());
        response.setPlaca(veiculo.getPlaca());
        response.setAno(veiculo.getAno());
        response.setCor(veiculo.getCor());
        response.setTipoVeiculo(veiculo.getTipoVeiculo());
        response.setAtivo(veiculo.getAtivo());

        return response;
    }

    public List<VeiculoResponseDTO> listar() {
//Aqui ele vai buscar todos os veículos no banco de dados, e guardado na variável veículos
        List<Veiculo> veiculos = veiculoRepository.findAll();
//Aqui nada mais é do que a criação de uma lista que irá retornar o DTO,
//retorna isso na variável "responses" e cria uma lista na memória.
        List<VeiculoResponseDTO> response = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            //cria um novo responseDTO para cada veículo que for buscado
            VeiculoResponseDTO responseDTO = new VeiculoResponseDTO();

            responseDTO.setIdVeiculo(veiculo.getIdVeiculo());
            responseDTO.setPlaca(veiculo.getPlaca());
            responseDTO.setMarca(veiculo.getMarca());
            responseDTO.setModelo(veiculo.getModelo());
            responseDTO.setAno(veiculo.getAno());
            responseDTO.setTipoVeiculo(veiculo.getTipoVeiculo());
            responseDTO.setCor(veiculo.getCor());
            responseDTO.setAtivo(veiculo.getAtivo());

            response.add(responseDTO);
        }
        return response;

    }


    public VeiculoResponseDTO atualizar(Long id, VeiculoRequestDTO request) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        veiculo.setPlaca(request.getPlaca());
        veiculo.setMarca(request.getMarca());
        veiculo.setModelo(request.getModelo());
        veiculo.setAno(request.getAno());
        veiculo.setTipoVeiculo(request.getTipoVeiculo());
        veiculo.setCor(request.getCor());
        veiculo.setAtivo(request.getAtivo());

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        VeiculoResponseDTO response = new VeiculoResponseDTO();

        response.setIdVeiculo(veiculoSalvo.getIdVeiculo());
        response.setPlaca(veiculoSalvo.getPlaca());
        response.setMarca(veiculoSalvo.getMarca());
        response.setModelo(veiculoSalvo.getModelo());
        response.setAno(veiculoSalvo.getAno());
        response.setTipoVeiculo(veiculoSalvo.getTipoVeiculo());
        response.setCor(veiculoSalvo.getCor());
        response.setAtivo(veiculoSalvo.getAtivo());

        return response;

    }

    public void desativar(Long id) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado"));

        veiculoRepository.delete(veiculo);

    }
}
