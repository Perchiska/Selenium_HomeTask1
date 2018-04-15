package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class GoodsPage extends MainPage {
    private static final String ADD_TO_CART = "add_cart_product";
    public GoodsPage(WebDriver driver) {
        super(driver);
    }
    public void addToCard(String number, String size){
        if (isElementPresented(By.name("options[Size]"))) {
            Select select = new Select(untilElementVisible(By.name("options[Size]")));
            select.selectByVisibleText(size);
            untilElementVisible(By.name(ADD_TO_CART)).click();
            wait.until(attributeContains(By.xpath("//span[@class='quantity']"),
                    "textContent", number));
        } else {
            untilElementVisible(By.name(ADD_TO_CART)).click();
            wait.until(attributeContains(By.xpath("//span[@class='quantity']"),
                    "textContent", number));
        }
    }

    public MainPage returnToMainPage() {
        untilElementVisible(By.cssSelector(".general-0")).click();
        return new MainPage(driver);
    }
}
