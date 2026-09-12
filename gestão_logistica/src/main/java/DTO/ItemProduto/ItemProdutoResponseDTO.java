package DTO.ItemProduto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemProdutoResponseDTO {

    private Long idItemProduto;

    private  String codigoProduto;

    private String subcodigoProduto;

}
