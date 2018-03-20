import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Selenium_task7 extends baseTest {
    public static final String URL = "http://localhost/litecart/admin/";
    public static final String USERNAME = "//input[@name='username']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//div[@class='footer']//button[@type='submit']";
    public static final String TABLE = "//div[@id='box-apps-menu-wrapper']";

    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();

        WebElement table = wait.until(visibilityOfElementLocated(By.xpath(TABLE)));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        List<WebElement> subrows;


        for (int i = 1; i < rows.size(); i++) {

            rows = driver.findElements(By.xpath("//div[@id='box-apps-menu-wrapper']//li/a/span[2]"));
            wait.until(ExpectedConditions.visibilityOf(rows.get(i - 1)));
            rows.get(i - 1).click();

            try {
                subrows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                        (By.xpath("//div[@id='box-apps-menu-wrapper']//li//ul//li")));

                int count = subrows.size();

                for (int n = 1; n < count; n++) {
                    subrows = driver.findElements(By.xpath("//div[@id='box-apps-menu-wrapper']//li//ul//li//a/span"));
                    wait.until(ExpectedConditions.visibilityOf(subrows.get(n - 1)));
                    subrows.get(n - 1).click();
                }
            } catch (org.openqa.selenium.TimeoutException ignored) {

            }


            Assert.assertTrue(isElementPresented(By.tagName("h1")));

        }
    }

}



