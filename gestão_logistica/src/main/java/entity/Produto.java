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
@Table(name = "produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "nome", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigoProduto;

    @Column(name = "descricao")
    private String descricaoProduto;

    @Column(name = "estoque", nullable = false)
    private int estoqueProduto;

    @Column(name = "preco", nullable = false)
    private double precoProduto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


}
