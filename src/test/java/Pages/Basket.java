package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class Basket extends MainPage {

    private static final String DELETE = "//li[1]//p[4]/button[@name='remove_cart_item']";
    private static final String SHORT = "//ul[@class='shortcuts']//li[1]";

    public Basket (WebDriver driver) {
        super(driver);
    }



    public MainPage returnToMainPage() {
        untilElementVisible(By.cssSelector(".general-0")).click();
        return new MainPage(driver);
    }

    public void deleteFromBasket(){
        if (isElementPresented(By.xpath(SHORT))) {
            untilElementVisible(By.xpath(SHORT)).click();
            whileElementIsPresentedClick(By.xpath(DELETE));
        } else {
            whileElementIsPresentedClick(By.xpath(DELETE));
        }
    }
}
