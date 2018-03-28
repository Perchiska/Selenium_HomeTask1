import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Selenium_task9 extends baseTest {

    public static final String URL = "http://localhost/litecart/admin/";
    public static final String USERNAME = "//input[@name='username']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//div[@class='footer']//button[@type='submit']";
    public static final String COUNTRIES = "//span[contains(text(), 'Countries')]";


    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();
        wait.until(visibilityOfElementLocated(By.xpath(COUNTRIES))).click();


        List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//tr[@class='row']")));
        ArrayList<String> country = new ArrayList<>();
        ArrayList<String> zones = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            try {
                rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//td[5]/a")));
                List<WebElement> zonesList = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//td[6]")));
                WebElement zone = zonesList.get(i);
                String zoneAttribute = zone.getAttribute("textContent");
                String contries = rows.get(i).getAttribute("textContent");
                if (zoneAttribute.equals("0")) {

                    country.add(contries);
                    System.out.println(country);

                } else {

                    rows.get(i).click();
                    country.add(wait.until(visibilityOfElementLocated(By.name("name"))).getAttribute("value"));

                    wait.until(presenceOfElementLocated(By.xpath("//*[@id='content']//h2")));
                    List<WebElement> zonesOfCountry = wait.until(visibilityOfAllElementsLocatedBy
                            (By.xpath("//table[@id='table-zones']//td[3]")));
                    for (int n = 0; n < zonesOfCountry.size() - 1; n++) {
                        zones.add(zonesOfCountry.get(n).getAttribute("textContent"));
                        System.out.println(zones);
                        ArrayList<String> sortedZones = new ArrayList<String>(zones);
                        Collections.sort(sortedZones);
                        Assert.assertTrue(zones.equals(sortedZones));
                        wait.until(presenceOfElementLocated(By.name("cancel"))).click();
                    }
                }
            } catch (org.openqa.selenium.TimeoutException ignored) {
            }

        }


    }
}





