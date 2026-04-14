package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");

    public void clickSignupLogin() {
        driver.findElement(signupLoginBtn).click();
    }
}