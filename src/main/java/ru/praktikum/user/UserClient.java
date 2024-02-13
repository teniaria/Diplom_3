package ru.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String SITE = "https://stellarburgers.nomoreparties.site/";
    private User user = new User();
    private final UserGenerator userGenerator = new UserGenerator();

    @Step("Создание пользователя через API.")
    public static Response createUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(SITE + "api/auth/register");
    }

    @Step("Удаление пользователя через API.")
    public static Response deleteUser(User user) {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(SITE + "api/auth/login")
                .body().path("accessToken");
        return given().contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .body(user).delete(SITE + "api/auth/user");
    }
}
