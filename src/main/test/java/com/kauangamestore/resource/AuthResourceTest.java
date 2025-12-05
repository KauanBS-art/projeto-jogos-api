package com.kauangamestore.resource;

import com.kauangamestore.dto.AuthDTO;
import com.kauangamestore.dto.UsuarioDTO;
import com.kauangamestore.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class AuthResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Test
    public void testLoginSucesso() {
        UsuarioDTO dto = new UsuarioDTO(
            "Usuario Teste Login",
            "teste.login@games.com",
            "123456",
            1
        );
        usuarioService.create(dto);

        AuthDTO auth = new AuthDTO("teste.login@games.com", "123456");

        given()
            .contentType(ContentType.JSON)
            .body(auth)
            .when().post("/auth")
            .then()
            .statusCode(200)
            .header("Authorization", notNullValue());
    }
}
