package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
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

    public Usuario add(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteById(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Id inválido: " + id);
        } else {
            usuarioRepository.deleteById(id);
        }
    }

    public void update(int id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Id inválido: " + id);
        } else {
            usuario.setId(id);
            usuarioRepository.save(usuario);
        }
    }

    public void validarAdministrador(int usuarioId) {
        Usuario usuario = getById(usuarioId);
        if (!usuario.isAdmin()) {
            throw new IllegalArgumentException("Acesso negado: usuário não é administrador");
        }
    }
}
