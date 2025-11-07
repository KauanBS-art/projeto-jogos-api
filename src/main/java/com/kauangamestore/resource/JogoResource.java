//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import com.kauangamestore.service.JogoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Response listarTodos() {
        List<JogoDTOResponse> jogos = jogoService.findAll();
        return Response.ok(jogos).build();
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
    @Transactional
    public Response criar(@Valid JogoDTO dto, @Context UriInfo uriInfo) {
        JogoDTOResponse created = jogoService.create(dto);
        if (created == null || created.id() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar jogo. Verifique se o ID da empresa existe.")
                    .build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.id()));
        return Response.created(builder.build()).entity(created).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid JogoDTO dto) {
        JogoDTOResponse jogo = jogoService.update(id, dto);
        return jogo == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(jogo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = jogoService.deletar(id);
        return deleted
            ? Response.noContent().build()
            : Response.status(Response.Status.NOT_FOUND).build();
    }
}
