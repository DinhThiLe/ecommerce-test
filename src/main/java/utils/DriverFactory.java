package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // 🔥 FIX ADS + POPUP (QUAN TRỌNG)
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-geolocation");

            // 👉 giảm bị detect automation
            options.addArguments("--disable-blink-features=AutomationControlled");

            driver = new ChromeDriver(options);

        } else {
            throw new RuntimeException("Browser not supported");
        }

        driver.manage().window().maximize();
        return driver;
    }
}