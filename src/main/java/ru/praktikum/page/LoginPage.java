package ru.praktikum.page;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.user.User;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    public final By loginHeader = By.xpath(".//h2[text() = 'Вход']");
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    //Поле "Пароль"
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");
    private final By registrationButton = By.linkText("Зарегистрироваться");
    private final By restorePassword = By.linkText("Восстановить пароль");
    User user;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы авторизации.")
    public LoginPage waitDownloadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(loginHeader).getText() != null
                && !driver.findElement(loginHeader).getText().isEmpty()
        ));
        return this;
    }

    @Step("Клик на кнопку 'Зарегистрироваться'.")
    public void clickRegistration() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик на кнопку 'Войти'.")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на кнопку 'Восстановить пароль'.")
    public void clickRestorePasswordButton() {
        driver.findElement(restorePassword).click();
    }

    @Step("Поиск поля email")
    public WebElement getEmail() {
        return driver.findElement(emailField);
    }

    @Step("Поиск поля password")
    public WebElement getPassword() {
        return driver.findElement(passwordField);
    }

    @Step("Заполнение поля email")
    public LoginPage setEmail(String newEmail) {
        getEmail().sendKeys(newEmail);
        return this;
    }
    @Step("Заполнение поля password")
    public LoginPage setPassword(String newPassword) {
        getPassword().sendKeys(newPassword);
        return this;
    }


    @DisplayName("Авторизация пользователя")
    public User login(User user) {
        waitDownloadLoginPage();
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLogin();
        return user;
    }
}
