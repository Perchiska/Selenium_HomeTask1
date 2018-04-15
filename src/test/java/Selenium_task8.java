import Pages.baseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Selenium_task8 extends baseTest {
    public static final String URL = "http://localhost/litecart/";

    @Test
    public void loginTest() {
        driver.get(URL);

        WebElement table = wait.until(visibilityOfElementLocated(By.cssSelector(" div.middle>div.content")));
        List<WebElement> rows = table.findElements(By.cssSelector("ul.products li"));

        for (WebElement row : rows) {
            try {
                WebElement sticker = row.findElement(By.cssSelector("a div div"));

                System.out.println(sticker.getText());
            } catch (TimeoutException ex) {
                System.out.println("Element is not presented");
            }
        }


    }
}
