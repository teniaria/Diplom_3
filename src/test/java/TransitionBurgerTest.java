import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.page.MainPage;
import static org.junit.Assert.assertEquals;

public class TransitionBurgerTest {
    private static BaseTest baseTest;
    private static final String site = baseTest.SITE;
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
        mainPage = new MainPage(driver);
        mainPage.waitLoadHeader();
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'.")
    @Description("Проверка перехода в раздел 'Булки'.")
    public void checkGoToBuns() {
        mainPage.clickBun();
        String bun = mainPage.checkActivity();
        assertEquals("Булки", bun);
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'.")
    @Description("Проверка перехода в раздел 'Соусы'.")
    public void checkGoToSauces() {
        mainPage.clickSauce();
        String sauce = mainPage.checkActivity();
        assertEquals("Соусы", sauce);
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'.")
    @Description("Проверка перехода в раздел 'Начинки'.")
    public void checkGoToFillings() {
        mainPage.clickFilling();
        String filling = mainPage.checkActivity();
        assertEquals("Начинки", filling);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
