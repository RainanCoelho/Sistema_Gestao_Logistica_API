package DTO.Pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponseDTO {

    private Long idPagamento;

    private String tipoPagamento;

    private String statusPagamento;

    private double valorPagamento;

}
