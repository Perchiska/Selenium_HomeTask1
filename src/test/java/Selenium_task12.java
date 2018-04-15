import Pages.baseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Selenium_task12 extends baseTest {

    private static final String URL = "http://localhost/litecart/admin/";
    private static final String USERNAME = "//input[@name='username']";
    private static final String PASSWORD = "//input[@name='password']";
    private static final String LOGIN_BUTTON = "//div[@class='footer']//button[@type='submit']";
    private static final String CATALOG = "//span[contains(text(), 'Catalog')]";
    private static final String ADD_NEW_PRODUCT = "//a[contains(text(), ' Add New Product')]";
    private static final String VALID_FROM = "date_valid_from";
    private static final String VALID_TO = "date_valid_to";
    private static final String QUANTITY = "quantity";
    private static final String CODE = "code";
    private static final String INFORMATION_TAB = "//a[contains(text(), 'Information')]";
    private static final String PRICES_TAB = "//a[contains(text(), 'Prices')]";
    private static final String PRICES_EUR = "prices[EUR]";
    private static final String PRICES_USD = "prices[USD]";
    private static final String NAME = "name[en]";
    private static final String GENDER = "[value='1-3']";
    private static final String SWITCH_INF_TAB = "//strong[contains(text(),'Manufacturer')]";
    private static final String SHORT_DESCRIPTION = "short_description[en]";
    private static final String KEYWORDS = "keywords";
    private static final String DESC = ".trumbowyg-editor";
    private static final String META_DESC = "meta_description[en]";
    private static final String TITLE = "head_title[en]";
    private static final String PRICES_PURCH = "purchase_price";
    private static final String PRICES_CHECK = "//h2[contains(text(), 'Prices')]";
    private static final String DOWNLOAD_FILE = "new_images[]";
    private final String filePath = "src/resourses" + this.getClass().getPackage().getName().replaceAll(".", "/") + "/zlee-net_01.jpg";
    private static final String SAVE_BUTTON = "[name='save']";

    public Selenium_task12(WebDriver driver) {
        super(driver);
    }


    @Test
    public void loginTest() {

        Path p1 = Paths.get(filePath);
        String absolutePath = p1.toAbsolutePath().toString();

        String productName = "pepper duck";

        System.out.println(absolutePath);

        driver.get(URL);
        clearAndSetText(By.xpath(USERNAME), "admin");
        clearAndSetText(By.xpath(PASSWORD), "admin");
        untilElementVisible(By.xpath(LOGIN_BUTTON)).click();
        untilElementVisible(By.xpath(CATALOG)).click();
        untilElementVisible(By.xpath(ADD_NEW_PRODUCT)).click();
        untilElementVisible(By.name(VALID_FROM)).sendKeys("11032018");
        untilElementVisible(By.name(VALID_TO)).sendKeys("11032019");
        clearAndSetText(By.name(QUANTITY), "10");
        clearAndSetText(By.name(CODE), "01010");
        clearAndSetText(By.name(NAME), productName);
        untilElementVisible(By.cssSelector(GENDER)).click();

        wait.until(visibilityOfElementLocated(By.name(DOWNLOAD_FILE))).sendKeys(absolutePath);

        untilElementVisible(By.xpath(INFORMATION_TAB)).click();

        wait.until(presenceOfElementLocated(By.xpath(SWITCH_INF_TAB)));

        clearAndSetText(By.name(SHORT_DESCRIPTION), "Short desc");
        clearAndSetText(By.name(KEYWORDS), "Pertseva");
        clearAndSetText(By.cssSelector(DESC), "Full description ololo ya voditel nlo");
        clearAndSetText(By.name(META_DESC), "Pertseva");
        clearAndSetText(By.name(TITLE), "Pertseva");

        Select selectManufacture = new Select(untilElementVisible(By.name("manufacturer_id")));
        selectManufacture.selectByVisibleText("ACME Corp.");

        untilElementVisible(By.xpath(PRICES_TAB)).click();
        wait.until(presenceOfElementLocated(By.xpath(PRICES_CHECK)));

        Select purchase_price = new Select(untilElementVisible(By.name("purchase_price_currency_code")));
        purchase_price.selectByVisibleText("US Dollars");
        clearAndSetText(By.name(PRICES_PURCH), "10");
        clearAndSetText(By.name(PRICES_USD), "10");
        clearAndSetText(By.name(PRICES_EUR), "8");

        untilElementVisible(By.cssSelector(SAVE_BUTTON)).click();
        Assert.assertTrue(isElementPresented(By.xpath("//a[contains(text(), 'pepper duck')]")));


    }


}

