package com.kauangamestore.resource;

import com.kauangamestore.model.Usuario;
import com.kauangamestore.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    public List<Usuario> listAll() {
        return usuarioRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Usuario getById(@PathParam("id") Long id) {
        return usuarioRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Usuario user) {
        Usuario existing = usuarioRepository.find("email", user.getEmail()).firstResult();
        if (existing != null) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("Email já cadastrado").build();
        }
        usuarioRepository.persist(user);
        return Response.created(URI.create("/usuarios/" + user.getId())).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Usuario updated) {
        Usuario entity = usuarioRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entity.setNome(updated.getNome());
        entity.setEmail(updated.getEmail());
        entity.setSenha(updated.getSenha());
        entity.setTipo(updated.getTipo());
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = usuarioRepository.deleteById(id);
        if (deleted) return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/login")
    public Response login(Usuario credentials) {
        Usuario user = usuarioRepository.find("email", credentials.getEmail()).firstResult();
        if (user != null && user.getSenha().equals(credentials.getSenha())) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();
    }
}