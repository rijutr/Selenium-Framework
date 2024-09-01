import Pages.*;
import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    private static final String PRODUCT = "ZARA COAT 3";
    private static final String COUNTRY = "India";
    private static final String CONFIRMATION_MESSAGE = "THANKYOU FOR THE ORDER.";
    private static final String TEST_DATA_FILE_PATH = "/src/main/resources/PurchaseOrder.json";

    @Test(dataProvider = "getData", groups = {"Submit Order"},
    description = "This test class will Submit Order in a Single Shot")
    public void submitOrder(HashMap<String,String> input) throws IOException {
        ProductCatalogue productCatalogue = Login.login(input.get("email"),input.get("password"));
        System.out.println(input.get("fn"));
        productCatalogue.addToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyProductDisplayOnCartPage(input.get("product")));
        CheckOutPage checkoutpage = cartPage.cilckCheckoutButton();
        checkoutpage.selectCountry(COUNTRY);
        SummaryPage summaryPage = checkoutpage.submit();
        Assert.assertTrue(summaryPage.getConfirmationMessage().equalsIgnoreCase(CONFIRMATION_MESSAGE));
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void testOrderHistoryPage(){
        ProductCatalogue productCatalogue = Login.login("test@cts.com", "Unni@123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyProductDisplayOnOrderPage(PRODUCT));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> firstData = new HashMap<>();
//        firstData.put("email","test@cts.com");
//        firstData.put("pass","Unni@123");
//        firstData.put("product","ZARA COAT 3");
//        HashMap<String,String> secondData = new HashMap<>();
//        firstData.put("email","test@cts.com");
//        firstData.put("pass","Unni@123");
//        firstData.put("product","ADIDAS ORIGINAL");
        List<HashMap<String,String>> data = getJsonToMap(TEST_DATA_FILE_PATH);
        System.out.println(data);
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}
