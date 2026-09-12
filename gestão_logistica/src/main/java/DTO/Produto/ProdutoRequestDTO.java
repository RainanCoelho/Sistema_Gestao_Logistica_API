package DTO.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    private String nomeProduto;

    private String descricaoProduto;

    private int estoqueProduto;

    private double precoProduto;

    private Long idCategoria;

}
