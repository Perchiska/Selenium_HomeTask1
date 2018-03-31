import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class baseTest {
    public WebDriver driver;
public WebDriverWait wait;

    @Before
    public void  start(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       wait = new WebDriverWait(driver, 10);


    }

    @After
    public void stop(){
        driver.close();
        driver=null;
    }

    public boolean  isElementPresented(By locator){
        try{
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        }
        catch (TimeoutException ex){
            return false;
        }
    }

    public  boolean areElementsPresented(By locator){
        return driver.findElements(locator).size()>0;
    }

    public WebElement visibilityOfElement(By locator){
        WebElement element =  this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public Boolean checkGrayColor(WebElement w){
        String colorOld = w.getCssValue("color");
        String c = colorOld.substring(colorOld.indexOf("("), colorOld.indexOf(")")).replaceAll(",", "");
        String formattedOne = c.split(" ")[0].replace("(", "");
        String formattedTwo =c.split(" ")[1];

        String formatted3 =c.split(" ")[2];

       return (formattedOne.equals(formattedTwo) & formattedTwo.equals(formatted3));
    }

    public Boolean checkRedColor(WebElement e){
        String campOld = e.getCssValue("color");
        String c = campOld.substring(campOld.indexOf("("), campOld.indexOf(")")).replaceAll(",", "");
        String campFormattedTwo =c.split(" ")[1];
        String campFormatted3 =c.split(" ")[2];
        return campFormatted3.equals(campFormattedTwo);
    }

    public WebDriver getDriver (ArrayList <WebDriver> list) {

        for (int l = 0; l < list.size(); l++) {
            System.out.println("Testing in Browser: " + list.get(l));
            WebDriver driver = list.get(l);


        }
        return driver;
    }



}
