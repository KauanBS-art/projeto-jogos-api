//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.PlataformaDTO;
import com.kauangamestore.dto.PlataformaDTOResponse;
import com.kauangamestore.service.PlataformaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/plataformas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlataformaResource {

    @Inject
    PlataformaService plataformaService;

    @GET
    public List<PlataformaDTOResponse> listarTodos() {
        return plataformaService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        PlataformaDTOResponse dto = plataformaService.findById(id);
        return dto == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(dto).build();
    }

    @POST
    public Response criar(@Valid PlataformaDTO dto, @Context UriInfo uriInfo) {
        PlataformaDTOResponse created = plataformaService.create(dto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid PlataformaDTO dto) {
        PlataformaDTOResponse updated = plataformaService.update(id, dto);
        return updated == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(updated).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        plataformaService.delete(id);
        return Response.noContent().build();
    }
}
