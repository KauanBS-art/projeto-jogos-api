package com.kauangamestore.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class JogoResourceTest {

    @Test
    void deveListarJogos() {
        given()
            .when().get("/jogos")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveCriarEDeletarJogo() {
        // Cria jogo com campos válidos e data formatada
        String location = given()
            .contentType(ContentType.JSON)
            .body("{"
                + "\"titulo\":\"Hollow Knight\","
                + "\"descricao\":\"Um metroidvania desafiador no subterrâneo de Hallownest.\","
                + "\"preco\":99.90,"
                + "\"dataLancamento\":\"09/08/2017\","
                + "\"idEmpresa\":1"
                + "}")
        .when()
            .post("/jogos")
        .then()
            .statusCode(201)
            .extract().header("Location");

        String id = location.substring(location.lastIndexOf('/') + 1);

        // Exclui o jogo criado
        given()
            .when().delete("/jogos/" + id)
            .then()
            .statusCode(204);
    }
}