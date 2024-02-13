package ru.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final By loginButton = By.linkText("Войти");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Войти'.")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
