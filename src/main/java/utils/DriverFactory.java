package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            // ✅ FIX CỨNG VERSION (tránh mismatch trên CI)
        	WebDriverManager.chromedriver()
            .driverVersion("147.0.0")
            .setup();
        	
            ChromeOptions options = new ChromeOptions();

            // 🔥 detect môi trường CI
            boolean isCI = System.getenv("CI") != null;

            if (isCI) {
                // ===== CHẠY TRÊN CI/CD =====
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            } else {
                // ===== CHẠY LOCAL =====
                options.addArguments("--start-maximized");
            }

            // 🔽 common options (áp dụng cho mọi môi trường)
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-geolocation");
            options.addArguments("--disable-blink-features=AutomationControlled");

            return new ChromeDriver(options);
        }

        throw new RuntimeException("Browser not supported: " + browser);
    }
}