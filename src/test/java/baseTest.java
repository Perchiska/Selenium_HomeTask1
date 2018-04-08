import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class baseTest {
    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public FluentWait fluentWait;


    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Element " + by+ " is found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println("Element " + throwable);
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Screenshot " + screen);
        }

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Getting element " + by);

        }
    }


    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        wait = new WebDriverWait(driver, 10);
        fluentWait = new FluentWait(driver);


    }


    @After
    public void stop() {
        driver.close();
        driver = null;
    }


    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }


    public boolean isElementPresented(By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean areElementsPresented(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public WebElement visibilityOfElement(String locator) {
        WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
        return element;
    }

    public Boolean checkGrayColor(WebElement w) {
        String colorOld = w.getCssValue("color");
        String c = colorOld.substring(colorOld.indexOf("("), colorOld.indexOf(")")).replaceAll(",", "");
        String formattedOne = c.split(" ")[0].replace("(", "");
        String formattedTwo = c.split(" ")[1];

        String formatted3 = c.split(" ")[2];

        return (formattedOne.equals(formattedTwo) & formattedTwo.equals(formatted3));
    }

    public Boolean checkRedColor(WebElement e) {
        String campOld = e.getCssValue("color");
        String c = campOld.substring(campOld.indexOf("("), campOld.indexOf(")")).replaceAll(",", "");
        String campFormattedTwo = c.split(" ")[1];
        String campFormatted3 = c.split(" ")[2];
        return campFormatted3.equals(campFormattedTwo);
    }

    public WebDriver getDriver(ArrayList<WebDriver> list) {

        for (int l = 0; l < list.size(); l++) {
            System.out.println("Testing in Browser: " + list.get(l));
            WebDriver driver = list.get(l);


        }
        return driver;
    }

    public WebElement untilElementVisible(By by) {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void clearAndSetText(By by, String text) {
        untilElementVisible(by).clear();
        if (text != null) {
            untilElementVisible(by).sendKeys(text);
        }
    }

    public void whileElementIsPresentedClick(By by) {
        while (isElementPresented(by)) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.click();
            wait.until(ExpectedConditions.stalenessOf(element));
           /* if(!isElementPresented(by)){
                break;
            }*/

        }
    }


}
