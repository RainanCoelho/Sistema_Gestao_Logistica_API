package service;

import entity.Categoria;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));
    }

    public Categoria atualizar(
            Long id,
            Categoria categoriaAtualizada) {

        Categoria categoria = buscarPorId(id);

        categoria.setNomeCategoria(categoriaAtualizada.getNomeCategoria());
        categoria.setDescricaoCategoria(categoriaAtualizada.getDescricaoCategoria());

        return categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {

        Categoria categoria = buscarPorId(id);

        categoriaRepository.delete(categoria);
    }
}


