//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.CategoriaDTO;
import com.kauangamestore.dto.CategoriaDTOResponse;
import com.kauangamestore.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    public List<CategoriaDTOResponse> listarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        CategoriaDTOResponse dto = service.findById(id);
        return dto == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(dto).build();
    }

    @POST
    public Response criar(@Valid CategoriaDTO dto, @Context UriInfo uriInfo) {
        CategoriaDTOResponse created = service.create(dto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid CategoriaDTO dto) {
        CategoriaDTOResponse updated = service.update(id, dto);
        return updated == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(updated).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}