package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.After;
import org.junit.Test;
import java.time.Duration;
import java.sql.Driver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class MainPage {
    private WebDriver driver;
    public  MainPage (WebDriver driver)
    {
        this.driver = driver;
    }
    public void acceptCookie() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    public boolean checkAccordionPanel()
    {
        Duration duration = Duration.ofSeconds(5);
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("accordion__heading-0"))));
        boolean checkingResult = true;
        WebElement element = driver.findElement(By.id("accordion__heading-7"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        duration = Duration.ofSeconds(3);
        new WebDriverWait(driver, duration);
        for (int i = 0; i < 8; i++)
        {
            driver.findElement(By.id("accordion__heading-"+i)).click();
            new WebDriverWait(driver, duration);
            if (!driver.findElement(By.id("accordion__panel-"+i)).isDisplayed())
            {
                checkingResult = false;
            }
        }
        return checkingResult;
    }
    //public  void moveToOrderPage() {
    //  driver.findElement(By.className("Button_Button__ra12g")).click();
    //Duration duration = Duration.ofSeconds(5);
    //new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("Button_Middle__1CSJM"))));

    //}

}
