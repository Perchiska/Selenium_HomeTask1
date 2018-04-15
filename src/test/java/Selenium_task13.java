import Pages.baseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Selenium_task13 extends baseTest {

    private static final String URL = "http://localhost/litecart/en/";
    private static final String ADD_TO_CART = "add_cart_product";
    private static final String CHECKOUT = "//a[contains(text(), 'Checkout »')]";
    private static final String DELETE = "//li[1]//p[4]/button[@name='remove_cart_item']";
    private static final String SHORT = "//ul[@class='shortcuts']//li[1]";

    public Selenium_task13(WebDriver driver) {
        super(driver);
    }


    @Test
    public void bucketTest() {

        driver.get(URL);

        for (int i = 1; i < 4; i++) {
            String ii = Integer.toString(i);
            wait.until(visibilityOfElementLocated(By.xpath("//div[@id='box-most-popular']//li[1]"))).click();
            if (isElementPresented(By.name("options[Size]"))) {
                Select select = new Select(untilElementVisible(By.name("options[Size]")));
                select.selectByVisibleText("Medium +$2.50");
                untilElementVisible(By.name(ADD_TO_CART)).click();
                wait.until(attributeContains(By.xpath("//span[@class='quantity']"),
                        "textContent", ii));
            } else {
                untilElementVisible(By.name(ADD_TO_CART)).click();
                wait.until(attributeContains(By.xpath("//span[@class='quantity']"),
                        "textContent", ii));
            }

            untilElementVisible(By.cssSelector(".general-0")).click();
        }

        untilElementVisible(By.xpath(CHECKOUT)).click();


        if (isElementPresented(By.xpath(SHORT))) {
            untilElementVisible(By.xpath(SHORT)).click();
            whileElementIsPresentedClick(By.xpath(DELETE));
        } else {
            whileElementIsPresentedClick(By.xpath(DELETE));
        }

        wait.until(visibilityOfElementLocated(By.xpath("//*[@id='checkout-cart-wrapper']//em")));


    }


}

