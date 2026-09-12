package DTO.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private String idProduto;

    private String nomeProduto;

    private String codigoProduto;

    private String descricaoProduto;

    private int estoqueProduto;

    private double precoProduto;

    private Long idCategoria;

    private String nomeCategoria;

    //Serve para caso o usuário clique no nome do vendedor seja
    // redirecionado para a página do mesmo
    private Long idUsuario;

    private String nomeVendedor;
}
