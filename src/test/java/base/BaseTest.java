package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        // ✅ chỉ cần gọi DriverFactory (đã xử lý CI bên trong)
        driver = DriverFactory.initDriver("chrome");

        // ❌ KHÔNG dùng implicit wait (tránh conflict)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // ✅ explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // mở trang
        driver.get("https://automationexercise.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}