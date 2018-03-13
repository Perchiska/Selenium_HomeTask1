import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class secondLessonThirdTask {

    public static final String URL ="http://localhost/litecart/admin/";
    public static final String USERNAME ="//input[@name='username']";
    public static final String PASSWORD ="//input[@name='password']";
    public static final String LOGIN_BUTTON ="//div[@class='footer']//button[@type='submit']";

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void  start(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        }

    @Test
    public void loginTest(){
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();
    }

    @After
    public void stop(){
        driver.close();
        driver=null;
    }

}



