package DTO.Entrega;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregaResponse {

    private Long idEntrega;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraEntrega;
    private String statusEntrega;

    private Long idMotorista;
    private String nomeMotorista;

    private Long idCliente;
    private String nomeCliente;

    private Long idProduto;
    private String nomeProduto;
    private String codigoProduto;
    private String subcodigoProduto;

    private Long idVeiculo;
    private String placaVeiculo;


}
