import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    private static final String PRODUCT = "ZARA COAT 3";

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        Actions a = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("test@cts.com");
        driver.findElement(By.id("userPassword")).sendKeys("Unni@123");
        driver.findElement(By.id("login")).click();

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement productName = products.stream()
                .filter(x -> x.findElement(By.cssSelector("b")).getText().contains(PRODUCT))
                .findFirst().orElse(null);
        assert productName != null;
        productName.findElement(By.cssSelector(".card-body .btn:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver
                .findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));
        boolean match = cartItems.stream().anyMatch(x -> x.getText().equalsIgnoreCase(PRODUCT));
        Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmMsg);
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.quit();
    }
}
