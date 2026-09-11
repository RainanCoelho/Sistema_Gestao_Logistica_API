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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome", nullable = false, length = 100)
    private String  nomeUsuario;

    @Column(name = "email", nullable = false,unique = true, length = 100)
    private String  emailUsuario;

    @Column(name = "senha", nullable = false, length = 255)
    private String  senhaUsuario;

    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private String tipoUsuario;

}
