import Pages.CartPage;
import Pages.ProductCatalogue;
import TestComponents.BaseTest;
import Utils.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    private static final String ERROR_MESSAGE = "Incorrect email or password.";
    private static final String PRODUCT = "ZARA COAT 3";
    private static final String PRODUCT_VERIFY = "ZARA COAT 3";

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void testIncorrectDetails() throws IOException {
        Login.login("test@cts.com", "Unni@1123");
        Assert.assertEquals(Login.getErrorMessage(), ERROR_MESSAGE);
    }

    @Test
    public void testProductValidation(){
        ProductCatalogue productCatalogue = Login.login("test@cts.com", "Unni@123");
        productCatalogue.addToCart(PRODUCT);
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertFalse(cartPage.verifyProductDisplayOnCartPage(PRODUCT_VERIFY));
    }
}
