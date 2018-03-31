import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Selenium_task10 extends baseTest {

    public static final String URL = "http://localhost/litecart/en/";
    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void ChromerightPage() {

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 15);
            driver.get(URL);

            List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

            for (int i = 0; i < rows.size(); i++) {


                rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

                WebElement priceRegularMainW = rows.get(i).findElement(By.xpath("//s[@class='regular-price']"));
                WebElement newCampaignMainW = rows.get(i).findElement(By.xpath("//strong[@class='campaign-price']"));
                WebElement nameW = rows.get(i).findElement(By.xpath("//div[@id='box-campaigns']//li//div[@class='name']"));

                String priceRegularMain = priceRegularMainW.getText();
                String newCampaignMain = newCampaignMainW.getText();
                String name = nameW.getAttribute("textContent");

                System.out.println(name + " " + newCampaignMain + " " + priceRegularMain);

                Dimension prRegM = priceRegularMainW.getSize();
                Dimension newCamM = newCampaignMainW.getSize();

                Assert.assertTrue((newCamM.height > prRegM.height & newCamM.width > prRegM.width), "Wrong size");
                Assert.assertTrue(checkGrayColor(priceRegularMainW), "Not gray");
                Assert.assertTrue(checkRedColor(newCampaignMainW), "Not red");

                System.out.println(newCampaignMainW.getCssValue("font-weight"));
                System.out.println(priceRegularMainW.getCssValue("text-decoration-line"));

                Assert.assertTrue((priceRegularMainW.getCssValue("text-decoration-line")
                        .equals("line-through")), "Wrong line stile");
                Assert.assertTrue((newCampaignMainW.getCssValue("font-weight")
                        .equals("700")), "Wrong font weight");

                rows.get(i).click();

                String duckPage = wait.until(visibilityOfElementLocated(By.cssSelector("h1.title")))
                        .getAttribute("textContent");
                System.out.println(duckPage);

                WebElement regularPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".regular-price")));
                WebElement campaignPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".campaign-price")));

                String regularPriceS = regularPrice.getAttribute("textContent");
                String campaignPriceS = campaignPrice.getAttribute("textContent");
                Dimension regPr = regularPrice.getSize();
                Dimension cmpPr = campaignPrice.getSize();

                Assert.assertTrue(name.equals(duckPage), "Invalid header");
                Assert.assertTrue(priceRegularMain.equals(regularPriceS), "Invalid regular price");
                Assert.assertTrue(newCampaignMain.equals(campaignPriceS), "Invalid new price");
                Assert.assertTrue(checkGrayColor(regularPrice), "Not gray");
                Assert.assertTrue(checkRedColor(campaignPrice), "Not red");
                Assert.assertTrue((regularPrice.getCssValue("text-decoration-line")
                        .equals("line-through")), "Wrong line stile");
                Assert.assertTrue((campaignPrice.getCssValue("font-weight")
                        .equals("700")), "Wrong font weight");
                Assert.assertTrue((cmpPr.height > regPr.height & cmpPr.width > regPr.width), "Wrong size");


                wait.until(visibilityOfElementLocated(By.cssSelector(".general-0"))).click();
                System.out.println("everything is ok");
            }




    }

    @Test
    public void FirefoxrightPage() {

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15);
        driver.get(URL);

        List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

        for (int i = 0; i < rows.size(); i++) {


            rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

            WebElement priceRegularMainW = rows.get(i).findElement(By.xpath("//s[@class='regular-price']"));
            WebElement newCampaignMainW = rows.get(i).findElement(By.xpath("//strong[@class='campaign-price']"));
            WebElement nameW = rows.get(i).findElement(By.xpath("//div[@id='box-campaigns']//li//div[@class='name']"));

            String priceRegularMain = priceRegularMainW.getText();
            String newCampaignMain = newCampaignMainW.getText();
            String name = nameW.getAttribute("textContent");

            System.out.println(name + " " + newCampaignMain + " " + priceRegularMain);

            Dimension prRegM = priceRegularMainW.getSize();
            Dimension newCamM = newCampaignMainW.getSize();

            Assert.assertTrue((newCamM.height > prRegM.height & newCamM.width > prRegM.width), "Wrong size");
            Assert.assertTrue(checkGrayColor(priceRegularMainW), "Not gray");
            Assert.assertTrue(checkRedColor(newCampaignMainW), "Not red");

            System.out.println(newCampaignMainW.getCssValue("font-weight"));
            System.out.println(priceRegularMainW.getCssValue("text-decoration-line"));

            Assert.assertTrue((priceRegularMainW.getCssValue("text-decoration-line")
                    .equals("line-through")), "Wrong line stile");
            Assert.assertTrue((newCampaignMainW.getCssValue("font-weight")
                    .equals("900")), "Wrong font weight");

            rows.get(i).click();

            String duckPage = wait.until(visibilityOfElementLocated(By.cssSelector("h1.title")))
                    .getAttribute("textContent");
            System.out.println(duckPage);


            WebElement regularPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".regular-price")));
            WebElement campaignPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".campaign-price")));
            System.out.println(campaignPrice.getCssValue("font-weight"));

            String regularPriceS = regularPrice.getAttribute("textContent");
            String campaignPriceS = campaignPrice.getAttribute("textContent");
            Dimension regPr = regularPrice.getSize();
            Dimension cmpPr = campaignPrice.getSize();

            Assert.assertTrue(name.equals(duckPage), "Invalid header");
            Assert.assertTrue(priceRegularMain.equals(regularPriceS), "Invalid regular price");
            Assert.assertTrue(newCampaignMain.equals(campaignPriceS), "Invalid new price");
            Assert.assertTrue(checkGrayColor(regularPrice), "Not gray");
            Assert.assertTrue(checkRedColor(campaignPrice), "Not red");
            Assert.assertTrue((regularPrice.getCssValue("text-decoration-line")
                    .equals("line-through")), "Wrong line stile");
            Assert.assertTrue((campaignPrice.getCssValue("font-weight")
                    .equals("700")), "Wrong font weight");
            Assert.assertTrue((cmpPr.height > regPr.height & cmpPr.width > regPr.width), "Wrong size");


            wait.until(visibilityOfElementLocated(By.cssSelector(".general-0"))).click();
            System.out.println("everything is ok");
        }

    }

    @Test
    public void edgePage() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.get(URL);

        List<WebElement> rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

        for (int i = 0; i < rows.size(); i++) {


            rows = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='box-campaigns']//li")));

            WebElement priceRegularMainW = rows.get(i).findElement(By.xpath("//s[@class='regular-price']"));
            WebElement newCampaignMainW = rows.get(i).findElement(By.xpath("//strong[@class='campaign-price']"));
            WebElement nameW = rows.get(i).findElement(By.xpath("//div[@id='box-campaigns']//li//div[@class='name']"));

            String priceRegularMain = priceRegularMainW.getText();
            String newCampaignMain = newCampaignMainW.getText();
            String name = nameW.getAttribute("textContent");

            System.out.println(name + " " + newCampaignMain + " " + priceRegularMain);

            Dimension prRegM = priceRegularMainW.getSize();
            Dimension newCamM = newCampaignMainW.getSize();

            Assert.assertTrue((newCamM.height > prRegM.height & newCamM.width > prRegM.width), "Wrong size");
            Assert.assertTrue(checkGrayColor(priceRegularMainW), "Not gray");
            Assert.assertTrue(checkRedColor(newCampaignMainW), "Not red");

            System.out.println(newCampaignMainW.getCssValue("font-weight"));
            System.out.println(priceRegularMainW.getCssValue("text-decoration"));

            Assert.assertTrue((priceRegularMainW.getCssValue("text-decoration")
                    .equals("line-through")), "Wrong line stile");
            Assert.assertTrue((newCampaignMainW.getCssValue("font-weight")
                    .equals("700")), "Wrong font weight");

            rows.get(i).click();

            String duckPage = wait.until(visibilityOfElementLocated(By.cssSelector("h1.title")))
                    .getAttribute("textContent");
            System.out.println(duckPage);


            WebElement regularPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".regular-price")));
            WebElement campaignPrice = wait.until(visibilityOfElementLocated(By.cssSelector(".campaign-price")));
            System.out.println(campaignPrice.getCssValue("font-weight"));

            String regularPriceS = regularPrice.getAttribute("textContent");
            String campaignPriceS = campaignPrice.getAttribute("textContent");
            Dimension regPr = regularPrice.getSize();
            Dimension cmpPr = campaignPrice.getSize();

            Assert.assertTrue(name.equals(duckPage), "Invalid header");
            Assert.assertTrue(priceRegularMain.equals(regularPriceS), "Invalid regular price");
            Assert.assertTrue(newCampaignMain.equals(campaignPriceS), "Invalid new price");
            Assert.assertTrue(checkGrayColor(regularPrice), "Not gray");
            Assert.assertTrue(checkRedColor(campaignPrice), "Not red");
            Assert.assertTrue((regularPrice.getCssValue("text-decoration")
                    .equals("line-through")), "Wrong line stile");
            Assert.assertTrue((campaignPrice.getCssValue("font-weight")
                    .equals("700")), "Wrong font weight");
            Assert.assertTrue((cmpPr.height > regPr.height & cmpPr.width > regPr.width), "Wrong size");


            wait.until(visibilityOfElementLocated(By.cssSelector(".general-0"))).click();
            System.out.println("everything is ok");
        }

    }
}








