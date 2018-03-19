import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
