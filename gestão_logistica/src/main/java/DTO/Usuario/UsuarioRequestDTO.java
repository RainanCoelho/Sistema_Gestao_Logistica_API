package DTO.Usuario;
//Essa classe define o que a API vai mostrar para o usuário


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    private Long idUsuario;

    private String nomeUsuario;

    private String senhaUsuario;

    private String emailUsuario;

    private String tipoUsuario;
}
