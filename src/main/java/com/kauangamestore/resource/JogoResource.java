//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import com.kauangamestore.service.JogoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JogoResource {

    @Inject
    JogoService jogoService;

    @GET
    public List<JogoDTOResponse> listarTodos() {
        return jogoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        JogoDTOResponse jogo = jogoService.findById(id);
        if (jogo == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(jogo).build();
    }


    @POST
    public Response criar(@Valid JogoDTO dto, @Context UriInfo uriInfo) {
        JogoDTOResponse created = jogoService.create(dto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.id()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid JogoDTO dto) {
        JogoDTOResponse jogo = jogoService.update(id, dto);
        return jogo == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(jogo).build();
    }

    // DELETAR
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = jogoService.deletar(id);
        if (deleted)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
