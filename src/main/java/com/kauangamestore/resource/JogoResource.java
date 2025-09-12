package com.kauangamestore.resource;

import com.kauangamestore.model.Jogo;
import com.kauangamestore.repository.JogoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JogoResource {

    @Inject
    JogoRepository jogoRepository;

    @GET
    public List<Jogo> listAll() {
        return jogoRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Jogo getById(@PathParam("id") Long id) {
        return jogoRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Jogo jogo) {
        jogoRepository.persist(jogo);
        return Response.created(URI.create("/jogos/" + jogo.getId())).entity(jogo).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Jogo updated) {
        Jogo j = jogoRepository.findById(id);
        if (j == null) return Response.status(Response.Status.NOT_FOUND).build();
        j.setTitulo(updated.getTitulo());
        j.setDescricao(updated.getDescricao());
        j.setPreco(updated.getPreco());
        j.setDataLancamento(updated.getDataLancamento());
        return Response.ok(j).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = jogoRepository.deleteById(id);
        if (deleted) return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}