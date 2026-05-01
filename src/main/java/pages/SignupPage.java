package pages;

import base.BasePage;
import org.openqa.selenium.*;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // ===== STEP 1 =====
    private By txtName = By.cssSelector("[data-qa='signup-name']");
    private By txtEmail = By.cssSelector("[data-qa='signup-email']");
    private By btnSignup = By.cssSelector("[data-qa='signup-button']");

    // ===== STEP 2: ACCOUNT INFO =====
    private By rdMr = By.id("id_gender1");
    private By txtPassword = By.id("password");

    private By selectDay = By.id("days");
    private By selectMonth = By.id("months");
    private By selectYear = By.id("years");

    private By chkNewsletter = By.id("newsletter");
    private By chkOffers = By.id("optin");

    // ===== ADDRESS INFO =====
    private By txtFirstName = By.id("first_name");
    private By txtLastName = By.id("last_name");
    private By txtCompany = By.id("company");
    private By txtAddress = By.id("address1");
    private By txtAddress2 = By.id("address2");

    private By selectCountry = By.id("country");

    private By txtState = By.id("state");
    private By txtCity = By.id("city");
    private By txtZip = By.id("zipcode");
    private By txtMobile = By.id("mobile_number");

    private By btnCreate = By.cssSelector("[data-qa='create-account']");

    // ===== VERIFY =====
    private By lblSuccess = By.xpath("//h2[@data-qa='account-created']");
    private By lblEmailExists = By.xpath("//p[contains(text(),'Email Address already exist')]");

    // ===== ACTION STEP 1 =====
    public void signup(String name, String email) {
        sendKeys(txtName, name);
        sendKeys(txtEmail, email);
        safeClick(btnSignup);
    }

    // ===== ACTION STEP 2 =====
    public void fillFullInfo(
            String password,
            String firstName,
            String lastName,
            String address
    ) {
        // Title
        safeClick(rdMr);

        // Account
        sendKeys(txtPassword, password);
        selectByVisibleText(selectDay, "1");
        selectByVisibleText(selectMonth, "January");
        selectByVisibleText(selectYear, "2000");

        safeClick(chkNewsletter);
        safeClick(chkOffers);

        // Address
        sendKeys(txtFirstName, firstName);
        sendKeys(txtLastName, lastName);
        sendKeys(txtCompany, "FPT");
        sendKeys(txtAddress, address);
        sendKeys(txtAddress2, "District 1");

        selectByVisibleText(selectCountry, "India");

        sendKeys(txtState, "HCM");
        sendKeys(txtCity, "HCM");
        sendKeys(txtZip, "700000");
        sendKeys(txtMobile, "0123456789");

       
        safeClick(btnCreate);
    }

    // ===== VERIFY =====
    public boolean isSuccess() {
        try {
            waitForElementVisible(lblSuccess);
            return driver.getCurrentUrl().contains("account_created");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailExistError() {
        return isElementDisplayed(lblEmailExists);
    }
}