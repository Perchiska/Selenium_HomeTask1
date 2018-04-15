import Pages.Helper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Selenium_task14 extends Helper {
    public static final String URL = "http://localhost/litecart/admin/";
    public static final String USERNAME = "//input[@name='username']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//div[@class='footer']//button[@type='submit']";
    public static final String COUNTRIES = "//span[contains(text(), 'Countries')]";
    public static final String AFG = "//a[contains(text(), 'Afghanistan')]";

    public Selenium_task14(WebDriver driver) {
        super(driver);
    }


    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();
        wait.until(visibilityOfElementLocated(By.xpath(COUNTRIES))).click();
        wait.until(visibilityOfElementLocated(By.xpath(AFG))).click();

        List<WebElement> listOfLinks = new ArrayList<>();
        listOfLinks = wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("form td a[href^='http']")));
        for(int i=0; i<listOfLinks.size();i++) {

            String mainWindow = driver.getWindowHandle();
            System.out.println(i+": Main window is opened:"+mainWindow);
            Set<String> oldWindows = driver.getWindowHandles();
            listOfLinks.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            System.out.println("New window is opened:"+newWindow);
            Assert.assertFalse(newWindow.equals(mainWindow));
            driver.close();
            driver.switchTo().window(mainWindow);
            System.out.println("Switch on :"+mainWindow);
        }


    }

}



