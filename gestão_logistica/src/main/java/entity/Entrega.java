package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
    private LocalDateTime dataHoraPrevista;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;

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
    @JoinColumn(name = "id_item_produto")
    private ItemProduto itemProduto;


    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

}
