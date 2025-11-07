package com.kauangamestore.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CategoriaResourceTest {

    @Test
    void deveListarCategorias() {
        given()
            .when().get("/categorias")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveCriarAtualizarExcluirCategoria() {
        // Cria nova categoria
        String location = given()
            .contentType(ContentType.JSON)
            .body("{\"nome\":\"Aventura\"}")
        .when()
            .post("/categorias")
        .then()
            .statusCode(201)
            .extract().header("Location");

        String id = location.substring(location.lastIndexOf('/') + 1);

        // Atualiza categoria
        given()
            .contentType(ContentType.JSON)
            .body("{\"nome\":\"Ação/Aventura\"}")
        .when()
            .put("/categorias/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));

        // Exclui categoria
        given()
            .when().delete("/categorias/" + id)
        .then()
            .statusCode(204);
    }
}