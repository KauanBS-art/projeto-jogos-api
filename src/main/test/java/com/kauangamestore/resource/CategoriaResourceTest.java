//Classe de Kauan Batista

package com.kauangamestore.resource;

import com.kauangamestore.dto.CategoriaDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class CategoriaResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/categorias")
            .then()
            .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testCreate() {
        CategoriaDTO dto = new CategoriaDTO("RPG");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/categorias")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", is("RPG"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testUpdate() {
        CategoriaDTO dto = new CategoriaDTO("Aventura");
        Integer id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/categorias")
            .then().statusCode(201).extract().path("id");

        CategoriaDTO updateDto = new CategoriaDTO("Ação e Aventura");

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .when().put("/categorias/" + id)
            .then()
            .statusCode(200)
            .body("nome", is("Ação e Aventura"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testDelete() {
        CategoriaDTO dto = new CategoriaDTO("Terror");
        Integer id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/categorias")
            .then().statusCode(201).extract().path("id");

        given()
            .when().delete("/categorias/" + id)
            .then()
            .statusCode(204);
    }
}
