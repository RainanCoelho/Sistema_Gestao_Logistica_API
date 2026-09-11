package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

    @Column(name = "tipo_pagamento")
    private String tipoPagamento;

    @Column(name = "status", nullable = false)
    private String statusPagamento;

    @Column(name = "valor", nullable = false)
    private double valorPagamento;



}
