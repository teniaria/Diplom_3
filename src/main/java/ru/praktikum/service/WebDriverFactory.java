package ru.praktikum.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getWebDriver() {
        switch (System.getProperty("browser", "chrome")) {
            case "yandex":
                System.setProperty("webdriver.yandex.driver", "src/main/resources/chromedriver");
                return new ChromeDriver();
            default:
                return new ChromeDriver();
        }
    }
}
