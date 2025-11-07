package com.kauangamestore.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class EmpresaResourceTest {

    @Test
    void deveListarEmpresas() {
        given()
            .when().get("/empresas")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveCriarAtualizarExcluirEmpresa() {

        // Criação com JSON na ordem correta
        String location = given()
            .contentType(ContentType.JSON)
            .body("{"
                + "\"nome\":\"FromSoftware\","
                + "\"paisOrigem\":\"Japão\","
                + "\"descricao\":\"Desenvolvedora japonesa criadora de Dark Souls e Elden Ring\""
                + "}")
        .when()
            .post("/empresas")
        .then()
            .statusCode(201)
            .extract().header("Location");

        String id = location.substring(location.lastIndexOf('/') + 1);

        // Atualização
        given()
            .contentType(ContentType.JSON)
            .body("{"
                + "\"nome\":\"FromSoftware Inc.\","
                + "\"paisOrigem\":\"Japão\","
                + "\"descricao\":\"Desenvolvedora japonesa de jogos de ação e fantasia\""
                + "}")
        .when()
            .put("/empresas/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));

        // Exclusão
        given()
            .when().delete("/empresas/" + id)
            .then()
            .statusCode(204);
    }
}