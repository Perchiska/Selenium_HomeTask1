import Pages.Helper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;
import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Selenium_task17 extends Helper {
    public static final String URL = "http://localhost/litecart/admin/";
    public static final String USERNAME = "//input[@name='username']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//div[@class='footer']//button[@type='submit']";
    public static final String CATALOG = "//span[contains(text(), 'Catalog')]";
    public static final String SUB_CATALOG = "#doc-catalog";
    public static final String DIRECTORY = "//a[contains(text(), 'Rubber Ducks')]";
    public static final String SUB_DIRECTORY = "//tr[4]/td[3]/a";

    public Selenium_task17(WebDriver driver) {
        super(driver);
    }


    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);

        driver.manage().logs().get("browser").filter(Level.SEVERE).forEach(l -> System.out.println(l));

        wait.until(visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();
        wait.until(visibilityOfElementLocated(By.xpath(CATALOG))).click();
        wait.until(visibilityOfElementLocated(By.cssSelector(SUB_CATALOG))).click();
        wait.until(visibilityOfElementLocated(By.xpath(DIRECTORY))).click();
        wait.until(visibilityOfElementLocated(By.xpath(SUB_DIRECTORY))).click();

        List<WebElement> tableList = wait.until(visibilityOfAllElementsLocatedBy
                (By.xpath("//table[@class='dataTable']//td[3]/a")));
        for (int i = 0; i < tableList.size(); i++) {
            try {

                List<WebElement> tableList2 = wait.until(visibilityOfAllElementsLocatedBy
                        (By.xpath("//table[@class='dataTable']//td[3]/a")));

                tableList2.get(i).click();
                List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
                Assert.assertTrue(logs.size() > 0);
                Assert.assertTrue(isElementPresented(By.cssSelector("#content h1")));
                wait.until(visibilityOfElementLocated(By.name("cancel"))).click();


            } catch (TimeoutException e) {
                System.out.println("Element is not found");
            }
        }


    }



}



