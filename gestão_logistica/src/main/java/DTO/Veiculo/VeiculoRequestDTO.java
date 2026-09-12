package DTO.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoRequestDTO {

    private String placa;

    private String marca;

    private String modelo;

    private int ano;

    private String cor;

    private String tipoVeiculo;

    private Boolean ativo;
}
