//Classe de Kauan Batista

package com.kauangamestore.resource;

import com.kauangamestore.dto.PlataformaDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class PlataformaResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/plataformas")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindById() {
        given()
            .when().get("/plataformas/1")
            .then()
            .statusCode(200)
            .body("nome", is("PC"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testCreate() {
        PlataformaDTO dto = new PlataformaDTO("Mega Drive");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/plataformas")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", is("Mega Drive"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testUpdate() {
        PlataformaDTO dto = new PlataformaDTO("PC Gamer");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/plataformas/1")
            .then()
            .statusCode(200)
            .body("nome", is("PC Gamer"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testDelete() {

        PlataformaDTO dto = new PlataformaDTO("Para Deletar");
        Integer id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/plataformas")
            .then().statusCode(201).extract().path("id");

        given()
            .when().delete("/plataformas/" + id)
            .then()
            .statusCode(204);
    }
}
