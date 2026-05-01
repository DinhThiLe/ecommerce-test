package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===== WAIT =====
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ===== SCROLL =====
    public void scrollToElement(By locator) {
        WebElement element = waitForElementPresent(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ===== SAFE CLICK (🔥 QUAN TRỌNG NHẤT) =====
    public void safeClick(By locator) {
        try {
            waitForElementClickable(locator).click();
        } catch (Exception e) {
            WebElement element = waitForElementPresent(locator);

            // scroll tới element trước
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);

            // click bằng JS (né iframe ads)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // ===== ACTIONS =====
    public void click(By locator) {
        safeClick(locator); // luôn dùng safeClick
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // ===== DROPDOWN =====
    public void selectByVisibleText(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        new Select(element).selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        WebElement element = waitForElementVisible(locator);
        new Select(element).selectByValue(value);
    }

    // ===== GET TEXT =====
    public String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    // ===== CHECK DISPLAY =====
    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}