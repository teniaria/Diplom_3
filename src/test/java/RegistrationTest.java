import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.page.LoginPage;
import ru.praktikum.page.MainPage;
import ru.praktikum.page.RegistrationPage;
import ru.praktikum.service.WebDriverFactory;
import ru.praktikum.user.UserClient;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {

    public static final String INVALID_PASSWORD = "Некорректный пароль";
    private static BaseTest baseTest;
    private static final String site = baseTest.SITE;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private WebDriver driver;
    private UserClient userClient;
    private final String expectedUrl = baseTest.SITE + "login";
    private String actual;
//    private final User user = new User();

    @Before
    public void startUp() {
        WebDriverFactory webDriver = new WebDriverFactory();
        WebDriverManager.chromedriver().setup();
        driver = webDriver.getWebDriver();
        driver.get(site);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        userClient = new UserClient();
    }

    @DisplayName("Переход на форму регистрации")
    private void toRegistrationPage() {
        mainPage.clickPersonalArea();
        loginPage.clickRegistration();
    }

    @Test
    @DisplayName("Корректная регистрация")
    @Description("Проверка корректной регистрации")
    public void createCorrectUserTest() {
        toRegistrationPage();
        registrationPage.createCorrectUser();
        loginPage.waitDownloadLoginPage();

        actual = driver.getCurrentUrl();
        assertEquals(expectedUrl, actual);
    }

    @Test
    @DisplayName("Некорректная регистрация")
    @Description("Проверка регистрации некорректного пользователя и сообщения: пароль меньше 6 символов")
    public void createInvalidUserTest() {
        toRegistrationPage();
        registrationPage.createInvalidUser();

        actual = registrationPage.getTextException();
        assertEquals(INVALID_PASSWORD, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
