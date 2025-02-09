package com.fiap.hackathon.user.bdd;

import com.fiap.challenge.user.core.domain.User;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition {

    private Response response;

    private User user;

    @Quando("criar um novo usuario")
    public void criar_um_novo_usuario() {
        response = given()
                .contentType(ContentType.JSON)
                .body(new User(1L, "Nome 1", "teste@teste.com.br", "0123456789", "123456"))
                .when()
                .post();
    }

    @Entao("deve retornar sucesso")
    public void deve_retornar_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Entao("deve retornar os dados do usuario")
    public void deve_retornar_os_dados_do_vusuario() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/UserResponse.schema.json"));
    }

    @Dado("um usuario que existe")
    public void um_usuario_que_existe() {
        user = new User(1L, "Nome 1", "teste@teste.com.br", "0123456789", "123456");
    }

    @Quando("obter o usuario")
    public void obter_o_usuario() {
        response = when()
                .get("/{id}", user.getId());
    }

    @Dado("um usuario que não existe")
    public void um_usuario_que_nao_existe() {
        user = new User(10L, "Nome 1", "teste@teste.com.br", "0123456789", "123456");
    }

    @Entao("deve retornar não encontrado")
    public void deve_retornar_nao_encontrado() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
