package service;

import entity.ItemProduto;
import entity.Produto;
import org.springframework.stereotype.Service;
import repository.ItemProdutoRepository;
import repository.ProdutoRepository;

import java.util.List;

@Service
public class ItemProdutoService {

    private final ItemProdutoRepository itemProdutoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemProdutoService(
            ItemProdutoRepository itemProdutoRepository,
            ProdutoRepository produtoRepository) {

        this.itemProdutoRepository = itemProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemProduto salvar(
            ItemProduto itemProduto,
            Long produtoId) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        itemProduto.setProduto(produto);

        return itemProdutoRepository.save(itemProduto);
    }

    public List<ItemProduto> listar() {
        return itemProdutoRepository.findAll();
    }

    public ItemProduto buscarPorId(Long id) {
        return itemProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item de produto não encontrado"));
    }

    public void excluir(Long id) {

        ItemProduto itemProduto = buscarPorId(id);

        itemProdutoRepository.delete(itemProduto);
    }

}
