package service;

import DTO.Usuario.UsuarioRequestDTO;
import DTO.Usuario.UsuarioResponseDTO;
import entity.Usuario;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaResponse(usuarioSalvo);
    }

    public List<UsuarioResponseDTO> listar() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return converterParaResponse(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return converterParaResponse(usuarioAtualizado);
    }

    public void excluir(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    private UsuarioResponseDTO converterParaResponse(Usuario usuario) {

        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setNomeUsuario(usuario.getNomeUsuario());
        response.setEmailUsuario(usuario.getEmailUsuario());
        response.setTipoUsuario(usuario.getTipoUsuario());

        return response;
    }
}
