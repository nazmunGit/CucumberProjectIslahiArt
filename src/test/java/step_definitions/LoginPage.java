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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


import java.time.Duration;
import java.util.Base64;

public class LoginPage {
   // private final WebDriver driver = Hooks.driver;
    private static WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
   public final static int TIMEOUT = 3;

    private static final Logger logger = LogManager.getLogger(LoginPage.class);


    // public final static int TIMEOUT = 5;
//    @Given("User is on IslahiArtLogIn page")
//    public void user_is_on_islahi_art_log_in_page() {
//        // Write code here that turns the phrase above into concrete actions
//
//    }
    @Before
    public void setUp() {

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
    @Given("User is on IslahiArtLogIn page {string}")
    public void loginTest(String url) {

        driver.get(url);
        //take_screenshot();

    }
    @When("User click on MyAccount icon")
    public void myAccountLogo(){
        driver.findElement(By.xpath("//a[@href='/my-account/']")).click();
    }
    //LoginPage lp = new LoginPage(driver);
    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {
        logger.info("Inserting username & password..");
        // login to application
        driver.findElement(By.xpath("(//input[(@placeholder='Username / Email')])[1]")).sendKeys(userName);
        driver.findElement(By.xpath("(//input[(@placeholder='Password')])[1]")).sendKeys(passWord);


    }

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {
        logger.info("Clicking on log in button");
        driver.findElement(By.xpath("(//button[(text() ='Sign in')])[3]")).submit();
        //logger.info("Verifying login..");
        // WebElement ele = driver.findElement(By.xpath("(//p[contains(text(),'Hello')])[1]"));
        //String innerHtml = ele.getAttribute("innerHTML");
        //String homePageHeading = driver.findElement(By.xpath("(//span[text()='Dashboard'])[1]")).getText();
        // logger.info("Login is successful.."+ innerHtml);
        //  Assert.assertEquals(homePageHeading, "Dashboard");
    }

    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        logger.info("Verifying login..");
        String actualErrorMessage = driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]")).getText();
        logger.info("Unsuccessful login...");
        //take_screenshot();
        // Verify Error Message
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
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
