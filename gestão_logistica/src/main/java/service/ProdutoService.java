package service;

import DTO.Produto.ProdutoRequestDTO;
import DTO.Produto.ProdutoResponseDTO;
import entity.Categoria;
import entity.Produto;
import entity.Usuario;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;
import repository.ProdutoRepository;
import repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository) {

        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO request, Long idVendedor) {

            // Busca a categoria informada.
            Categoria categoria = categoriaRepository
                    .findById(request.getIdCategoria())
                    .orElseThrow(() ->
                            new RuntimeException("Categoria não encontrada"));

            // Busca o usuário que será associado ao produto.
            Usuario vendedor = usuarioRepository.findById(idVendedor)
                    .orElseThrow(() ->
                            new RuntimeException("Usuário não encontrado"));

            // Verifica se o usuário possui o tipo VENDEDOR.
            if (!"VENDEDOR".equals(String.valueOf(vendedor.getTipoUsuario()))) {
                throw new RuntimeException("O usuário precisa ser um vendedor");
            }

            // Preenche a entidade com os dados recebidos.
            Produto produto = new Produto();

            produto.setNomeProduto(request.getNomeProduto());
            produto.setDescricaoProduto(request.getDescricaoProduto());
            produto.setEstoqueProduto(request.getEstoqueProduto());
            produto.setPrecoProduto(request.getPrecoProduto());

            // Gera o código e associa categoria e vendedor.
            produto.setCodigoProduto(UUID.randomUUID().toString());
            produto.setCategoria(categoria);
            produto.setUsuario(vendedor);

            // Salva o produto.
            Produto produtoSalvo = produtoRepository.save(produto);

            // Prepara a resposta.
            ProdutoResponseDTO response = new ProdutoResponseDTO();

            response.setIdProduto(produtoSalvo.getIdProduto());
            response.setNomeProduto(produtoSalvo.getNomeProduto());
            response.setCodigoProduto(produtoSalvo.getCodigoProduto());
            response.setDescricaoProduto(produtoSalvo.getDescricaoProduto());
            response.setEstoqueProduto(produtoSalvo.getEstoqueProduto());
            response.setPrecoProduto(produtoSalvo.getPrecoProduto());

            response.setIdCategoria(categoria.getIdCategoria());
            response.setNomeCategoria(categoria.getNomeCategoria());

            response.setIdUsuario(vendedor.getIdUsuario());
            response.setNomeVendedor(vendedor.getNomeUsuario());

            return response;
        }

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    private ProdutoResponseDTO converterParaResponse(Produto produto) {

        ProdutoResponseDTO response = new ProdutoResponseDTO();

        response.setIdProduto(produto.getIdProduto());
        response.setNomeProduto(produto.getNomeProduto());
        response.setCodigoProduto(produto.getCodigoProduto());
        response.setDescricaoProduto(produto.getDescricaoProduto());
        response.setEstoqueProduto(produto.getEstoqueProduto());
        response.setPrecoProduto(produto.getPrecoProduto());

        response.setIdCategoria(produto.getCategoria().getIdCategoria());
        response.setNomeCategoria(produto.getCategoria().getNomeCategoria());

        response.setIdUsuario(produto.getUsuario().getIdUsuario());
        response.setNomeVendedor(produto.getUsuario().getNomeUsuario());

        return response;
    }

    public ProdutoResponseDTO buscarPorId(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        return converterParaResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO request) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository
                .findById(request.getIdCategoria())
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));

        produto.setNomeProduto(request.getNomeProduto());
        produto.setDescricaoProduto(request.getDescricaoProduto());
        produto.setEstoqueProduto(request.getEstoqueProduto());
        produto.setPrecoProduto(request.getPrecoProduto());
        produto.setCategoria(categoria);

        Produto produtoAtualizado = produtoRepository.save(produto);

        return converterParaResponse(produtoAtualizado);
    }

    public void excluir(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

}
