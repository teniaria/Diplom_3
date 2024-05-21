package ru.praktikum.page;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.user.UserGenerator;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final By registrationHeader = By.xpath(".//h2[text() = 'Регистрация']");
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    // Поле "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Поле "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By invalidPassword = By.xpath(".//*[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    private final By loginButton = By.linkText("Войти");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Ожидание загрузки страницы с регистрацией.")
    public void waitDownloadRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(registrationHeader).getText() != null
                && !driver.findElement(registrationHeader).getText().isEmpty()
        ));
    }

    @Step("Клик по кнопке 'Зарегистрироваться'.")
    public void clickRegistrationUser() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке 'Войти'.")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Получение сообщения о некорректном пароле")
    public String getTextException() {
        return driver.findElement(invalidPassword).getText();
    }

    @Step("Заполнение поля name")
    public RegistrationPage setName(String newName) {
        waitDownloadRegistrationPage();
        getName().sendKeys(newName);
        return this;
    }

    @Step("Заполнение поля email")
    public RegistrationPage setEmail(String newEmail) {
        getEmail().sendKeys(newEmail);
        return this;
    }

    @Step("Заполнение поля password")
    public RegistrationPage setPassword(String newPassword) {
        getPassword().sendKeys(newPassword);
        return this;
    }

    @Step("Поиск поля name")
    public WebElement getName() {
        return driver.findElement(nameField);
    }

    @Step("Поиск поля email")
    public WebElement getEmail() {
        return driver.findElement(emailField);
    }

    @Step("Поиск поля password")
    public WebElement getPassword() {
        return driver.findElement(passwordField);
    }

    @DisplayName("Регистрация корректного пользователя")
    public void createCorrectUser() {
        waitDownloadRegistrationPage();
        UserGenerator userGenerator = new UserGenerator();
        setName(userGenerator.getName());
        setEmail(userGenerator.getEmail());
        setPassword(userGenerator.getValidPassword());
        clickRegistrationUser();
    }

    @DisplayName("Регистрация некорректного пользователя: пароль меньше 6 символов")
    public void createInvalidUser() {
        waitDownloadRegistrationPage();
        UserGenerator userGenerator = new UserGenerator();
        setName(userGenerator.getName());
        setEmail(userGenerator.getEmail());
        setPassword(userGenerator.getInvalidPassword());
        clickRegistrationUser();
    }
}
