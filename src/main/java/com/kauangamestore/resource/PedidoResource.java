package com.kauangamestore.resource;

import com.kauangamestore.dto.PedidoDTO;
import com.kauangamestore.dto.PedidoResponseDTO;
import com.kauangamestore.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @Inject
    SecurityContext securityContext;

    @POST
    @RolesAllowed({"CLIENTE", "ADMINISTRADOR"}) 
    public Response realizarPedido(PedidoDTO dto) {
        String email = securityContext.getUserPrincipal().getName();
        
        PedidoResponseDTO retorno = service.realizarPedido(dto, email);
        return Response.status(201).entity(retorno).build();
    }

    @GET
    @Path("/meus-pedidos")
    @RolesAllowed({"CLIENTE", "ADMINISTRADOR"})
    public Response meusPedidos() {
        String email = securityContext.getUserPrincipal().getName();
        List<PedidoResponseDTO> lista = service.meusPedidos(email);
        return Response.ok(lista).build();
    }
}
