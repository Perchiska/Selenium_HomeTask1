import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
    public static final String GEO = "//span[contains(text(), 'Geo Zones')]";


    @Test
    public void countries() {
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
                    System.out.println(country.get(i));

                } else {

                    rows.get(i).click();
                    country.add(wait.until(visibilityOfElementLocated(By.cssSelector("[name='name']"))).getAttribute("value"));

                    wait.until(presenceOfElementLocated(By.xpath("//*[@id='content']//h2")));
                    List<WebElement> zonesOfCountry = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='table-zones']//tr")));
                    int count = zonesOfCountry.size() - 2;
                    for (int n = 1; n < count; n++) {
                        zonesOfCountry = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='table-zones']//tr//td[3]")));

                        zones.add(zonesOfCountry.get(n).getAttribute("textContent"));


                    }
                    ArrayList<String> sortedZones = new ArrayList<String>(zones);
                    Collections.sort(sortedZones);
                    System.out.println(sortedZones);
                    Assert.assertTrue(zones.equals(sortedZones));
                    zones.clear();

                    wait.until(visibilityOfElementLocated(By.xpath(COUNTRIES))).click();
                }



            } catch (org.openqa.selenium.TimeoutException ignored) {
            }


        }
        ArrayList<String> sortedCountries = new ArrayList<>(country);
        Collections.sort(sortedCountries);
        System.out.println(sortedCountries);
        Assert.assertTrue(country.equals(sortedCountries));

    }

    @Test
    public void geoZones() {

        driver.get(URL);
        wait.until(visibilityOfElementLocated(By.xpath(USERNAME))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(PASSWORD))).sendKeys("admin");
        wait.until(visibilityOfElementLocated(By.xpath(LOGIN_BUTTON))).click();
        wait.until(visibilityOfElementLocated(By.xpath(GEO))).click();

        ArrayList<String> listOfZones = new ArrayList<>();
        List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//tr[@class='row']")));

        for(int i=0; i<rows.size(); i++){
            rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//tr[@class='row']//td[5]/a")));
            rows.get(i).click();

            List<WebElement> zonesList = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='table-zones']//td[3]")));

            try{

                for( int n =0; n<zonesList.size(); n++){
                    zonesList = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]//select"));
                    Select select = new Select(zonesList.get(n));
                    WebElement option = select.getFirstSelectedOption();
                    String defaultItem = option.getText();
                    System.out.println(defaultItem );

                    listOfZones.add(defaultItem);

                }
                ArrayList<String> sortedZones = new ArrayList<String>(listOfZones);
                Collections.sort(sortedZones);
                System.out.println(sortedZones);
                Assert.assertTrue(listOfZones.equals(sortedZones));
                listOfZones.clear();
                wait.until(visibilityOfElementLocated(By.xpath(GEO))).click();
            } catch (org.openqa.selenium.TimeoutException ignored){

            }


        }

    }

}





