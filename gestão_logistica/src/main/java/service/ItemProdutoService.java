package service;

import DTO.ItemProduto.ItemProdutoResponseDTO;
import entity.ItemProduto;
import entity.Produto;
import org.springframework.stereotype.Service;
import repository.ItemProdutoRepository;
import repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public ItemProdutoResponseDTO salvar(Long idProduto) {

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        ItemProduto itemProduto = new ItemProduto();

        itemProduto.setProduto(produto);
        itemProduto.setSubcodigoProduto(UUID.randomUUID().toString());

        ItemProduto itemSalvo = itemProdutoRepository.save(itemProduto);

        return converterParaResponse(itemSalvo);
    }

    public List<ItemProdutoResponseDTO> listar() {

        List<ItemProduto> itens = itemProdutoRepository.findAll();

        List<ItemProdutoResponseDTO> responses = new ArrayList<>();

        for (ItemProduto item : itens) {
            ItemProdutoResponseDTO response = converterParaResponse(item);
            responses.add(response);
        }

        return responses;
    }

    public ItemProdutoResponseDTO buscarPorId(Long id) {

        ItemProduto item = itemProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item do produto não encontrado"));

        return converterParaResponse(item);
    }

    public void excluir(Long id) {

        ItemProduto item = itemProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item do produto não encontrado"));

        itemProdutoRepository.delete(item);
    }

    private ItemProdutoResponseDTO converterParaResponse(ItemProduto item) {

        ItemProdutoResponseDTO response = new ItemProdutoResponseDTO();

        response.setIdItemProduto(item.getIdItemProduto());
        response.setCodigoProduto(item.getProduto().getCodigoProduto());
        response.setSubcodigoProduto(item.getSubcodigoProduto());

        return response;
    }

}
