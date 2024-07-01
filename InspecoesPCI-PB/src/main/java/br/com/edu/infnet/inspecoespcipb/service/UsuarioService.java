package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.UsuarioDTO;
import br.com.edu.infnet.inspecoespcipb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
    }

    public Usuario add(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        Usuario usuario = new Usuario(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.isAdmin()
        );
        return usuarioRepository.save(usuario);
    }

    public void deleteById(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Id inválido: " + id);
        } else {
            usuarioRepository.deleteById(id);
        }
    }

    public void update(int id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setAdmin(usuarioDTO.isAdmin());

        usuarioRepository.save(usuario);

    }

    public void validarAdministrador(int usuarioId) {
        Usuario usuario = getById(usuarioId);
        if (!usuario.isAdmin()) {
            throw new IllegalArgumentException("Acesso negado: usuário não é administrador");
        }
    }
}
