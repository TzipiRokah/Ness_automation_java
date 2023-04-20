import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitUntilVisbiltyElemntLocated(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilVisbiltyElemntClickbel(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findElemnt(By locator)
    {
        return driver.findElement(locator);
    }

    public List<WebElement> findElemnts(By locator)
    {
        return driver.findElements((locator));
    }

    public String getText(By locator)
    {
        return driver.findElement(locator).getText();
    }

    public void typeInto(String inpytText, By locator)
    {
        findElemnt(locator).sendKeys(inpytText);
    }

    public WebElement click(By locator)
    {
        findElemnt(locator).click();
        return findElemnt(locator);
    }

    public void closeTab()
    {
        ArrayList<String> switchTabs= new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(switchTabs.get(0));
        driver.close();
        driver.switchTo().window(switchTabs.get(1));
    }
}
