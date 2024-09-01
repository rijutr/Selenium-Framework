package TestComponents;

import Pages.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    protected LoginPage Login;

    public WebDriver initializeDriver() throws IOException {

        // System.getProperties("user.dir") -> Returns the current working directory
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
        } else {
            throw new RuntimeException("Browser is provided in the test.properties file not supported."
                    + "Please provide chrome,firefox or msedge");
        }
        return driver;
    }

    private static WebDriver getDriver() throws IOException {
        WebDriver driver = null;
        String browser = null;
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/test.properties");
        prop.load(fileInputStream);
        String s = System.getProperty("browser") != null ? browser = System.getProperty("browser") : prop.getProperty("browser");

        if (s.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (s.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920,1080));
        }
        return driver;
    }

    public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {
        String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                + filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshot(String testCasName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "/src/test/Reports/" + testCasName + ".png"));
        return System.getProperty("user.dir") + "/src/test/Reports/" + testCasName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initializeDriver();
        Login = new LoginPage(driver);
        Login.navigateToLoginPage();
        return Login;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.quit();
    }
}
