//Classe de Kauan batista

package com.kauangamestore.resource;

import com.kauangamestore.dto.JogoDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class JogoResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/jogos")
            .then()
            .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testCreate() {
        JogoDTO dto = new JogoDTO(
            "Jogo Teste Unitario",
            "Descricao Teste",
            150.00,
            10,
            LocalDate.now(),
            1L,
            List.of(1L)
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/jogos")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("titulo", is("Jogo Teste Unitario"))
            .body("estoque", is(10));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testUpdate() {
        JogoDTO dto = new JogoDTO(
            "Zelda Atualizado",
            "Nova descricao",
            350.00,
            40,
            LocalDate.of(2017, 3, 3),
            1L,
            List.of(4L)
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/jogos/1")
            .then()
            .statusCode(200)
            .body("titulo", is("Zelda Atualizado"));
    }
}
