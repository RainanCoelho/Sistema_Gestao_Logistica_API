package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long idEntrega;

    @Column(name = "data_hora_prevista")
    private LocalDate dataHoraPrevista;

    @Column(name = "data_hora_entrega")
    private LocalDate dataHoraEntrega;

    @Column(name = "status")
    private String statusEntrega;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Usuario motorista;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

}
