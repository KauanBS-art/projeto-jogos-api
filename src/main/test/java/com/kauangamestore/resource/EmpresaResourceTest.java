//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.EmpresaDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class EmpresaResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/empresas")
            .then()
            .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testCreate() {
        EmpresaDTO dto = new EmpresaDTO(
            "Sega",
            "Japão",
            "Criadora do Sonic"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/empresas")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", is("Sega"));
    }
}
