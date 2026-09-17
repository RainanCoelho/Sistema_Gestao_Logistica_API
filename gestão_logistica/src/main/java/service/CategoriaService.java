package service;

import DTO.Categoria.CategoriaRequestDTO;
import DTO.Categoria.CategoriaResponseDTO;
import entity.Categoria;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDTO salvar(CategoriaRequestDTO request) {

        Categoria categoria = new Categoria();

        categoria.setNomeCategoria(request.getNomeCategoria());
        categoria.setDescricaoCategoria(request.getDescricaoCategoria());

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return converterParaResponse(categoriaSalva);
    }

    public List<CategoriaResponseDTO> listar() {

        List<Categoria> categorias = categoriaRepository.findAll();

        List<CategoriaResponseDTO> responses = new ArrayList<>();

        for (Categoria categoria : categorias) {
            responses.add(converterParaResponse(categoria));
        }

        return responses;
    }

    public CategoriaResponseDTO buscarPorId(Long id) {

        Categoria categoria = buscarEntidadePorId(id);

        return converterParaResponse(categoria);
    }

    public CategoriaResponseDTO atualizar(
            Long id,
            CategoriaRequestDTO request) {

        Categoria categoria = buscarEntidadePorId(id);

        categoria.setNomeCategoria(request.getNomeCategoria());
        categoria.setDescricaoCategoria(request.getDescricaoCategoria());

        Categoria categoriaAtualizada = categoriaRepository.save(categoria);

        return converterParaResponse(categoriaAtualizada);
    }

    public void excluir(Long id) {

        Categoria categoria = buscarEntidadePorId(id);

        categoriaRepository.delete(categoria);
    }

    private Categoria buscarEntidadePorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));
    }

    private CategoriaResponseDTO converterParaResponse(Categoria categoria) {

        CategoriaResponseDTO response = new CategoriaResponseDTO();

        response.setIdCategoria(categoria.getIdCategoria());
        response.setNomeCategoria(categoria.getNomeCategoria());
        response.setDescricaoCategoria(categoria.getDescricaoCategoria());

        return response;
    }
}


