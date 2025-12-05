package com.kauangamestore.resource;

import com.kauangamestore.dto.ItemPedidoDTO;
import com.kauangamestore.dto.PedidoDTO;
import com.kauangamestore.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    JwtService jwtService;

    @Test
    @TestSecurity(user = "jondoe@gmail.com", roles = {"CLIENTE"})
    public void testRealizarPedido() {
        ItemPedidoDTO item = new ItemPedidoDTO(3L, 1);

        PedidoDTO pedidoDTO = new PedidoDTO(
            List.of(item),
            2,
            "77000-000",
            "Rua das Flores",
            "123",
            "Palmas"
        );

        given()
            .contentType(ContentType.JSON)
            .body(pedidoDTO)
            .when().post("/pedidos")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("total", is(199.9F))
            .body("modoPagamento", is("BOLETO"));
    }

    @Test
    @TestSecurity(user = "jondoe@gmail.com", roles = {"CLIENTE"})
    public void testMeusPedidos() {
        given()
            .when().get("/pedidos/meus-pedidos")
            .then()
            .statusCode(200)
            .body("size()", is(1));
    }

    @Test
    public void testSemAuth() {
        given()
            .contentType(ContentType.JSON)
            .when().get("/pedidos/meus-pedidos")
            .then()
            .statusCode(401);
    }
}
