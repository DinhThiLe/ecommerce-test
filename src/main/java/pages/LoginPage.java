package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ===== LOCATORS =====
    private By txtEmail = By.cssSelector("[data-qa='login-email']");
    private By txtPassword = By.cssSelector("[data-qa='login-password']");
    private By btnLogin = By.cssSelector("[data-qa='login-button']");
    
    private By lblLoginSuccess = By.xpath("//a[contains(text(),'Logged in as')]");
    private By lblLoginError = By.xpath("//p[contains(text(),'incorrect')]");

    // ===== ACTIONS =====
    public void login(String email, String password) {
        sendKeys(txtEmail, email);
        sendKeys(txtPassword, password);
        click(btnLogin);
    }

    // ===== VERIFY =====
    public boolean isLoginSuccess() {
        return isElementDisplayed(lblLoginSuccess);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(lblLoginError);
    }
}