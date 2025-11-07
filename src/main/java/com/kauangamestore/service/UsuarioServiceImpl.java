//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.UsuarioDTO;
import com.kauangamestore.dto.UsuarioDTOResponse;
import com.kauangamestore.model.Usuario;
import com.kauangamestore.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    private UsuarioDTOResponse toResponse(Usuario usuario) {
        return new UsuarioDTOResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Override
    @Transactional
    public UsuarioDTOResponse create(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuarioRepository.persist(usuario);
        return toResponse(usuario);
    }

    @Override
    public List<UsuarioDTOResponse> findAll() {
        return usuarioRepository.listAll()
            .stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTOResponse findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        return usuario != null ? toResponse(usuario) : null;
    }

    @Override
    @Transactional
    public UsuarioDTOResponse update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null)
            return null;

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        return toResponse(usuario);
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        return usuarioRepository.deleteById(id);
    }
}
