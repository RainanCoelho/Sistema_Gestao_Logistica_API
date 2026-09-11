package DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Essa classe define o que o usuário vai mandar para a API
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private String nomeUsuario;

    private String emailUsuario;

    private String senhaUsuario;

    private String tipoUsuario;


}
