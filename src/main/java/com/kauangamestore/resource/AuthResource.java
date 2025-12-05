package com.kauangamestore.resource;

import com.kauangamestore.dto.AuthDTO;
import com.kauangamestore.dto.UsuarioDTOResponse;
import com.kauangamestore.model.Usuario;
import com.kauangamestore.repository.UsuarioRepository;
import com.kauangamestore.service.HashService;
import com.kauangamestore.service.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthDTO dto) {
        String hash = hashService.getHashSenha(dto.senha());

        Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.email(), hash);

        if (usuario != null) {
            String token = jwtService.generateJwt(usuario);
            return Response.ok()
                    .header("Authorization", token)
                    .entity(UsuarioDTOResponse.valueOf(usuario))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
