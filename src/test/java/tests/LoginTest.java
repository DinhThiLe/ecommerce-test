package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage login;

    @BeforeMethod
    public void setUpLoginPage() {
        new HomePage(driver).clickSignupLogin();
        login = new LoginPage(driver);
    }

    @Test
    public void loginSuccess() {
        login.login("dinhthile1711@gmail.com", "241003Le.");
        Assert.assertTrue(login.isLoginSuccess());
    }

    @Test
    public void loginFail() {
        login.login("valid@gmail.com", "wrongpass");
        Assert.assertTrue(login.isErrorDisplayed());
    }
}