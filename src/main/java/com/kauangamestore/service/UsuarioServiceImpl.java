//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.UsuarioDTO;
import com.kauangamestore.dto.UsuarioDTOResponse;
import com.kauangamestore.model.Perfil;
import com.kauangamestore.model.Usuario;
import com.kauangamestore.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    public List<UsuarioDTOResponse> findAll() {
        return repository.listAll().stream()
                .map(UsuarioDTOResponse::valueOf).toList();
    }

    @Override
    public UsuarioDTOResponse findById(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) throw new NotFoundException("Usuário não encontrado");
        return UsuarioDTOResponse.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTOResponse create(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        Perfil perfil = Perfil.valueOf(dto.idPerfil() != null ? dto.idPerfil() : 3);
        usuario.setPerfil(perfil);

        repository.persist(usuario);
        return UsuarioDTOResponse.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTOResponse update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) throw new NotFoundException("Usuário não encontrado");

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        
        if (dto.senha() != null && !dto.senha().isBlank()) {
             usuario.setSenha(hashService.getHashSenha(dto.senha()));
        }

        if (dto.idPerfil() != null) {
            usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));
        }

        return UsuarioDTOResponse.valueOf(usuario);
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }
    

    public Usuario findByEmailAndSenha(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha);
    }
}
