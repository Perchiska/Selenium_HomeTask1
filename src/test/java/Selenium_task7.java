import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Selenium_task7 extends baseTest {
    public static final String URL ="http://localhost/litecart/admin/";
    public static final String USERNAME ="//input[@name='username']";
    public static final String PASSWORD ="//input[@name='password']";
    public static final String LOGIN_BUTTON ="//div[@class='footer']//button[@type='submit']";
    public static final String TABLE = "//ul[@id='box-apps-menu']";

    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TABLE)));
        List<WebElement> rows = table.findElements(By.tagName("li"));
        for(WebElement row : rows){

           WebElement field = row.findElement(By.xpath("//a/span[2]"));
           field.click();
           Thread.sleep(3000);
          /*// Assert.assertTrue(isElementPresented(By.tagName("h1")));
            List<WebElement> subfields = driver.findElements(By.xpath("//*[@id='box-apps-menu']//ul//li"));
              for(WebElement row2: subfields){
                  WebElement subfield = row2.findElement(By.xpath("//a//span"));
                  field.click();
              } */

        }
    }

}
