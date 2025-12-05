//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import com.kauangamestore.service.JogoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JogoResource {

    @Inject
    JogoService jogoService;

    @GET
    public Response listarTodos() {
        return Response.ok(jogoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        JogoDTOResponse jogo = jogoService.findById(id);
        if (jogo == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(jogo).build();
    }

    @POST
    @RolesAllowed("ADMINISTRADOR")
    public Response criar(JogoDTO dto, @Context UriInfo uriInfo) {
        JogoDTOResponse created = jogoService.create(dto);
        if (created == null) return Response.status(Response.Status.BAD_REQUEST).build();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.id()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMINISTRADOR")
    public Response atualizar(@PathParam("id") Long id, JogoDTO dto) {
        JogoDTOResponse jogo = jogoService.update(id, dto);
        if (jogo == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(jogo).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMINISTRADOR")
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = jogoService.deletar(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
