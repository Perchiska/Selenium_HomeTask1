import Pages.Basket;
import Pages.GoodsPage;
import Pages.MainPage;
import Pages.baseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Selenium_task19 extends baseTest {

    private static final String URL = "http://localhost/litecart/en/";
    public Selenium_task19(WebDriver driver) {
        super(driver);
    }
    MainPage mainpage = new MainPage(driver);
    Basket basket = new Basket(driver);
    GoodsPage goods = new GoodsPage(driver);

    @Test
    public void bucketTest() {
        driver.get(URL);
        for (int i = 1; i < 4; i++) {
            String number = Integer.toString(i);
            mainpage.clickOnFirstElementInMostPopular();
            goods.addToCard(number, "Medium +$2.50");
            goods.returnToMainPage();
        }

        mainpage.clickOnCheckOutButton();
        basket.deleteFromBasket();
        wait.until(visibilityOfElementLocated(By.xpath("//*[@id='checkout-cart-wrapper']//em")));


    }


}

