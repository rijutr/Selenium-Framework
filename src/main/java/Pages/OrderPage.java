package Pages;

import Utils.ReusableMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends ReusableMethod {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productName;

    public boolean verifyProductDisplayOnOrderPage(String prodName){
        return productName.stream().anyMatch(x -> x.getText().equalsIgnoreCase(prodName));
    }
}

