package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends baseTest {
    private static final String CHECKOUT = "//a[contains(text(), 'Checkout »')]";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public Basket clickOnFirstElementInMostPopular()
    {
        visibilityOfElement("//div[@id='box-most-popular']//li[1]").click();
        return new Basket(driver);
    }

    public Basket clickOnCheckOutButton(){
        untilElementVisible(By.xpath(CHECKOUT)).click();
        return new Basket(driver);
    }

}
