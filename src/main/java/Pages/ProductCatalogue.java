package Pages;

import Utils.ReusableMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends ReusableMethod {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    public List<WebElement> getProducts() {
        waitForElementToAppear(By.cssSelector(".mb-3"));
        return products;
    }

    public WebElement getProductName(String productName){
        return getProducts().stream()
                .filter(x -> x.findElement(By.cssSelector("b")).getText().contains(productName))
                .findFirst().orElse(null);
    }
    public void addToCart(String productName){
        WebElement prod = getProductName(productName);
        prod.findElement(By.cssSelector(".card-body .btn:last-of-type")).click();
        waitForElementToAppear(By.id("toast-container"));
        waitForElementToDisappear(spinner);
    }
}
