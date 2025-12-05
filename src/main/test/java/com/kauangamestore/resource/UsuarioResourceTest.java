//Classe de Kauan Batista Silveira

package com.kauangamestore.resource;

import com.kauangamestore.dto.UsuarioDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusTest
public class UsuarioResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = {"ADMINISTRADOR"})
    public void testFindAll() {
        given()
            .when().get("/usuarios")
            .then()
            .statusCode(200);
    }

    @Test
    public void testCreate() {
        UsuarioDTO dto = new UsuarioDTO(
            "Novo Usuario Teste",
            "teste@email.com",
            "123456",
            2
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/usuarios")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("email", is("teste@email.com"))
            .body("senha", nullValue()); 
    }
}
