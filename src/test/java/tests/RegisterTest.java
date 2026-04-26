package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;

public class RegisterTest extends BaseTest {

    SignupPage signup;

    @BeforeMethod
    public void goToSignup() {
        new HomePage(driver).clickSignupLogin();
        signup = new SignupPage(driver);
    }

    // ===== TC01: Đăng ký thành công =====
    @Test
    public void TC01_Register_Success() {

        String email = "auto" + System.currentTimeMillis() + "@gmail.com";

        signup.signup("Sinh", email);

        signup.fillFullInfo(
                "123456",
                "Sinh",
                "Nguyen",
                "HCM"
        );
     // DEBUG
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println(driver.getPageSource());

        Assert.assertTrue(signup.isSuccess(), "Đăng ký thành công!");
    }

    // ===== TC02: Email đã tồn tại =====
    @Test
    public void TC02_Register_Email_Exists() {

        String email = "dinhthile1711@gmail.com";

        signup.signup("Sinh", email);

        Assert.assertTrue(signup.isEmailExistError(),
                "Không hiển thị lỗi email đã tồn tại");
    }

    // ===== TC03: Thiếu Name =====
    @Test
    public void TC03_Register_Empty_Name() {

        signup.signup("", "auto@gmail.com");

        Assert.assertTrue(driver.getPageSource().contains("Signup"),
                "Không xử lý khi thiếu Name");
    }

    // ===== TC04: Thiếu Email =====
    @Test
    public void TC04_Register_Empty_Email() {

        signup.signup("Sinh", "");

        Assert.assertTrue(driver.getPageSource().contains("Signup"),
                "Không xử lý khi thiếu Email");
    }

    // ===== TC05: Email sai format =====
    @Test
    public void TC05_Register_Invalid_Email() {

        signup.signup("Sinh", "abc@");

        Assert.assertTrue(driver.getPageSource().contains("Signup"),
                "Không validate email");
    }

    // ===== TC06: Thiếu Password =====
    @Test
    public void TC06_Register_Empty_Password() {

        String email = "auto" + System.currentTimeMillis() + "@gmail.com";

        signup.signup("Sinh", email);

        signup.fillFullInfo(
                "",
                "Sinh",
                "Nguyen",
                "HCM"
        );

        Assert.assertFalse(signup.isSuccess(),
                "Vẫn tạo account khi thiếu password!");
    }
}