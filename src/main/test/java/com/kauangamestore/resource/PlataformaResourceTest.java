package com.kauangamestore.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PlataformaResourceTest {

    @Test
    void deveListarPlataformas() {
        given()
            .when().get("/plataformas")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveCriarAtualizarExcluirPlataforma() {
        // Cria nova plataforma
        String location = given()
            .contentType(ContentType.JSON)
            .body("{\"nome\":\"PlayStation 5\"}")
        .when()
            .post("/plataformas")
        .then()
            .statusCode(201)
            .extract().header("Location");

        String id = location.substring(location.lastIndexOf('/') + 1);

        // Atualiza plataforma
        given()
            .contentType(ContentType.JSON)
            .body("{\"nome\":\"PS5\"}")
        .when()
            .put("/plataformas/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));

        // Exclui plataforma
        given()
            .when().delete("/plataformas/" + id)
        .then()
            .statusCode(204);
    }
}