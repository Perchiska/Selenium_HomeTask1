import Pages.Helper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Selenium_task11 extends Helper {
    public static final String URL = "http://localhost/litecart/";
    public static final String NEW_CUSTOMER = "//a[contains(text(), 'New customers click here')]";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String ADDRESS1 = "address1";
    public static final String POSTCODE = "postcode";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String COUNTRY = "country_code";
    public static final String ZONE = "//select[@name='zone_code']";
    public static final String DESIRED_PASSWORD = "password";
    public static final String CONFIRMED_PASSWORD = "confirmed_password";
    public static final String CREATE_AK_BUTTON = "create_account";
    public static final String LOGOUT_BUTTON = "//a[contains(text(), 'Logout')]";
    public static final String LOGIN_BUTTON = "//button[contains(text(), 'Login')]";
    public static final String CITY= "city";

    public Selenium_task11(WebDriver driver) {
        super(driver);
    }


    @Test
    public void loginTest() {

        driver.get(URL);

        Random rand = new Random();
        int  number = rand.nextInt(89999) + 10000;
        String numberToString = Integer.toString(number);

        String randomEmail = "dhdhhd"+numberToString+"@mail.ru";
        String passwordS = "111aA";

        untilElementVisible(By.xpath(NEW_CUSTOMER)).click();
        clearAndSetText(By.name(FIRST_NAME), "KatriNN");
        clearAndSetText(By.name(LAST_NAME), "Pertseva");
        clearAndSetText(By.name(ADDRESS1), "aaaa 55-55");
        clearAndSetText(By.name(POSTCODE), numberToString);
        clearAndSetText(By.name(EMAIL), randomEmail);
        clearAndSetText(By.name(DESIRED_PASSWORD), passwordS);
        clearAndSetText(By.name(CONFIRMED_PASSWORD), passwordS);
        clearAndSetText(By.name(PHONE), "+796599"+numberToString);
        clearAndSetText(By.name(CITY), "dffdf");

        Select select = new Select(untilElementVisible(By.name(COUNTRY)));
        select.selectByValue("US");

        Select selectZone = new Select(untilElementVisible(By.xpath(ZONE)));
        List<WebElement> list = selectZone.getOptions();
        int size = list.size();
        selectZone.selectByIndex(rand.nextInt(size));


        untilElementVisible(By.name(CREATE_AK_BUTTON)).click();
        untilElementVisible(By.xpath(LOGOUT_BUTTON)).click();
        clearAndSetText(By.name(EMAIL), randomEmail);
        clearAndSetText(By.name(DESIRED_PASSWORD), passwordS);
        untilElementVisible(By.xpath(LOGIN_BUTTON)).click();
        untilElementVisible(By.xpath(LOGOUT_BUTTON)).click();




    }


    }

