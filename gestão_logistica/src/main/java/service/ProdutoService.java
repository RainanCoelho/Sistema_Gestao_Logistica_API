package service;

import entity.Categoria;
import entity.Produto;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;
import repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository) {

        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto salvar(Produto produto, Long categoriaId) {

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));

        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));
    }

    public Produto atualizar(
            Long id,
            Produto produtoAtualizado,
            Long categoriaId) {

        Produto produto = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));

        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setCodigoProduto(produtoAtualizado.getCodigoProduto());
        produto.setPrecoProduto(produtoAtualizado.getPrecoProduto());
        produto.setEstoqueProduto(produtoAtualizado.getEstoqueProduto());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }

}
