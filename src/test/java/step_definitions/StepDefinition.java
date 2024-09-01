package step_definitions;

import Pages.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinition extends BaseTest {
    private LoginPage loginPage;
    private ProductCatalogue productCatalogue;
    private SummaryPage summaryPage;

    @Given("I landed on ecommerce page")
    public void landOnEcommercePage() throws IOException {
        loginPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void loginUsingUnameAndPass(String uname, String password) {
        productCatalogue = loginPage.login(uname, password);
    }

    @When("^I add product (.+) to Cart$")
    public void addProductToCart(String productName) {
        productCatalogue.addToCart(productName);
    }

    @When("^checkout (.+) and submit the order$")
    public void checkoutAndSubmitOrder(String productName) {
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyProductDisplayOnCartPage(productName));
        CheckOutPage checkoutpage = cartPage.cilckCheckoutButton();
        checkoutpage.selectCountry("India");
        summaryPage = checkoutpage.submit();
    }

    @Then("{string} message is displayed on the ConfirmationPage")
    public void messageVerification(String confirmMessage) {
        Assert.assertTrue(summaryPage.getConfirmationMessage().equalsIgnoreCase(confirmMessage));
        driver.quit();
    }

    @Then("{string} message displayed")
    public void errorValidationOnLoginFailure(String errorMessage) {
        Assert.assertEquals(errorMessage, "Incorrect email or password.");
    }


}
