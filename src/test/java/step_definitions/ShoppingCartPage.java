package step_definitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;
import java.util.Base64;

public class ShoppingCartPage {
    private static WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    public final static int TIMEOUT = 3;
    private static final Logger logger = LogManager.getLogger(ShoppingCartPage.class);
    static String ssLocation = "screenshots/ss"+ System.currentTimeMillis()+".png";
    // private final WebDriver driver = Hooks.driver;

    @Before
    public void setUp() {
//    String appUrl = "https://islahiart.com/";
//        driver = new ChromeDriver();
//        driver.get(appUrl);
//        driver.manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        ExtentSparkReporter reporter = new ExtentSparkReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        //create a new test
        test = extent.createTest("Islahi Art");

    }
    @Given("User is on home page {string}")
    public void loginTest(String url) {

        driver.get(url);
        //take_screenshot();

    }
    //
//    @When("User username as {string} and password as {string}")
//    public void user_username_as_and_password_as(String string, String string2) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
    @When("User click on BestSelling link")
    public void clickBestSellingLink() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.xpath("//a[contains(text(),'Best Selling')]"))).perform();
    }

    @Then("User click on item1 {string} link into a cart")
    public void clickSquareFrametoCart(String str1) {
        driver.findElement(By.xpath("(//h2[@class='woocommerce-loop-product__title'])[1]")).click();
        driver.findElement(By.xpath("//button[@value='9292']")).click();
    }

    @Then("User navigating to BestSelling link")
    public void backToBestSelling() {
        driver.findElement(By.xpath("//a[text()='Best Selling']")).click();
    }
    @Then("User click on item2 {string} link into a cart")
    public void clickKidsTeetoCart(String str2) {
        driver.findElement(By.xpath("//h2[text()='Kids Comfortable Cotton Tee']")).click();
        driver.findElement(By.xpath("//button[@class='single_add_to_cart_button button alt'][text()='Add to cart']")).click();
    }
    @Then("User should be able to see {string} and {string} on Shopping Cart")
    public void viewShoppingCart(String str3, String str4) {
        driver.findElement(By.xpath("(//span[@class='ast-icon icon-bag'])[1]")).click();
    }

    @After
    public void closeBrowser(Scenario scenario){
        if (scenario.isFailed()) {
            //Take the screenshot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //Add it to the report
            test.addScreenCaptureFromPath("data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot));
        } else {
            //Take the screenshot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //Add it to the report
            test.addScreenCaptureFromPath("data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot));
            test.pass("Test passed");
        }
        extent.flush();
        driver.quit();
    }
}
