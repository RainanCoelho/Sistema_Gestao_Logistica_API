package entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "ano")
    private int ano;

    @Column(name = "tipo_veiculo")
    private String tipoVeiculo;

    @Column(name = "cor")
    private String cor;

    @Column(name = "ativo")
    private Boolean ativo;

}
