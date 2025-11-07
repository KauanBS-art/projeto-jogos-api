//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.EmpresaDTO;
import com.kauangamestore.dto.EmpresaDTOResponse;
import com.kauangamestore.service.EmpresaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {

    @Inject
    EmpresaService empresaService;

    @GET
    public Response findAll() {
        List<EmpresaDTOResponse> empresas = empresaService.findAll();
        return Response.ok(empresas).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EmpresaDTOResponse empresa = empresaService.findById(id);
        if (empresa == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(empresa).build();
    }

    @POST
    @Transactional
    public Response criar(@Valid EmpresaDTO dto, @Context UriInfo uriInfo) {
        EmpresaDTOResponse created = empresaService.create(dto);
        if (created == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar empresa. Verifique os dados enviados.")
                    .build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid EmpresaDTO dto) {
        EmpresaDTOResponse updated = empresaService.update(id, dto);
        return updated == null
            ? Response.status(Response.Status.NOT_FOUND).build()
            : Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        empresaService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
