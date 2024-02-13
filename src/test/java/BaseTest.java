import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.page.*;
import ru.praktikum.service.WebDriverFactory;
import ru.praktikum.user.User;
import ru.praktikum.user.UserClient;
import ru.praktikum.user.UserGenerator;

public class BaseTest {
    protected static final String SITE = "https://stellarburgers.nomoreparties.site/";
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected RestorePasswordPage restorePasswordPage;
    protected InfoUserPage infoUserPage;
    protected User user;
    public UserClient userClient;
    private final UserGenerator userGenerator = new UserGenerator();


    @Before
    @DisplayName("Создание переменных и пользователя")
    public void create() {
        WebDriverFactory webDriver = new WebDriverFactory();
        WebDriverManager.chromedriver().setup();
        driver = webDriver.getWebDriver();
        driver.get(SITE);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        infoUserPage = new InfoUserPage(driver);
        user = userGenerator.getUser();

        userClient.createUser(user);
    }

    @After
    @DisplayName("Выход из браузера и удаление пользователя")
    public void delete() {
        userClient.deleteUser(user);
        driver.quit();
    }
}
