//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.UsuarioDTO;
import com.kauangamestore.dto.UsuarioDTOResponse;
import com.kauangamestore.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<UsuarioDTOResponse> listarTodos() {
        return usuarioService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        UsuarioDTOResponse usuario = usuarioService.findById(id);
        if (usuario == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(usuario).build();
    }

    @POST
    public Response criar(@Valid UsuarioDTO dto, @Context UriInfo uriInfo) {
        UsuarioDTOResponse created = usuarioService.create(dto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.id()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid UsuarioDTO dto) {
        UsuarioDTOResponse usuario = usuarioService.update(id, dto);
        return usuario == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(usuario).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = usuarioService.deletar(id);
        if (deleted)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
