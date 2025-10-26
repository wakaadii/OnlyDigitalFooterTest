package lib.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.regex.Pattern;

public class MainPageObject {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPageObject (WebDriver driver, String site) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(site);
    }

    By getLocatorByString(String locatorWithType) {
        String [] exploitedLocator = locatorWithType.split(Pattern.quote("="), 2);
        String byType = exploitedLocator[0];
        String locator = exploitedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        }else if (byType.equals("class")) {
            return By.className(locator);
        } else if (byType.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator. Locator - " + locatorWithType);
        }
    }

    public WebElement waitForElementPresents(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(by)
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresents(String locator) {
        By by = this.getLocatorByString(locator);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }


    public void swipeUpToElement(String locator, String errorMessage, int maxSwipes)
            {
        int alreadySwiped = 0;
        WebElement element = this.waitForElementPresents(locator, errorMessage);
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes){
                Assertions.assertTrue(element.isDisplayed(), errorMessage);
                break;
            }
            swipeWebPageUp();
            ++alreadySwiped;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        swipeWebPageUp();
    }

    public void swipeWebPageUp(){
        JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
        JSExecutor.executeScript("window.scrollBy(0, 500)");
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresents(locator, "can't find element by locator " + locator).getLocation().getY();
        JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
        Object js_result= JSExecutor.executeScript("return window.pageYOffset");
        elementLocationByY -= Integer.parseInt(js_result.toString());

        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public WebElement clickOnElement(String locator, String errorMessage){
        WebElement element = waitForElementPresents(locator, errorMessage);
        element.click();
        return element;
    }

}
