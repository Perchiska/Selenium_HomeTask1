import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class Selenium_task10 extends baseTest {

    public static final String URL = "http://localhost/litecart/";

    @Test
    public void rightPage(){
      driver.get(URL);
        String prMain = driver.findElement(By.xpath("//s[@class='regular-price']")).getText();

        List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

        for(int i=0; i<rows.size();i++){
            rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));
            String priceMain = rows.get(i).findElement(By.xpath("//s[@class='regular-price']")).getText();
            String newPriceMain = rows.get(i).findElement(By.xpath("//strong[@class='campaign-price']")).getText();
            String name = rows.get(i).findElement(By.xpath("//div[@class='name']")).getText();
            System.out.println(priceMain);
        }

    }


}


