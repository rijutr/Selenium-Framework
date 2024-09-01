package Pages;

import Utils.ReusableMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends ReusableMethod {
    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder*='Country']")
    WebElement selectCountry;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement selectDesiredCountry;

    @FindBy(css = ".action__submit")
    WebElement submit;

    public void selectCountry(String county){
        Actions a = new Actions(driver);
        a.sendKeys(selectCountry, county).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results.list-group"));
        selectDesiredCountry.click();
    }
    public SummaryPage submit(){
        submit.click();
        return new SummaryPage(driver);
    }
}

