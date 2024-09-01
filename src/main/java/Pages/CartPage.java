package Pages;

import Utils.ReusableMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends ReusableMethod {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cart h3")
    List<WebElement> cartItems;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkout;

    public boolean verifyProductDisplayOnCartPage(String prodName){
        return cartItems.stream().anyMatch(x -> x.getText().equalsIgnoreCase(prodName));
    }

    public CheckOutPage cilckCheckoutButton(){
        checkout.click();
        return new CheckOutPage(driver);
    }

}
