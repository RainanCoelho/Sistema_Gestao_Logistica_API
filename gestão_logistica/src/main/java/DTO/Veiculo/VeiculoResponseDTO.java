package DTO.Veiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Essa classe define o que o usuário vai mandar para a API
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponseDTO {

    private Long idVeiculo;

    private String placa;

    private String marca;

    private String modelo;

    private int ano;

    private String cor;

    private String tipoVeiculo;

    private Boolean ativo;

}
