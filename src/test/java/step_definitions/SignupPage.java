package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class SignupPage {
    static String ssLocation = "screenshots/ss"+ System.currentTimeMillis()+".png";

    private static final Logger logger = LogManager.getLogger(SignupPage.class);

    private static WebDriver driver;
    public final static int TIMEOUT = 5;

    public void take_screenshot(){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(ssLocation));
        } catch (Exception e) {
            // throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }
    @Before
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

    }
    @Given("User is on IslahiArtSignUp page {string}")
    public void homePage (String url) {
        driver.get(url);
        take_screenshot();
    }
    @When("User click on MyAccount logo")
    public void myAccountPage() {
        logger.info("Going to My Account page..");
        driver.findElement(By.xpath("//a[@href='/my-account/']")).click();
        take_screenshot();
    }
    @Then("User click on Sign Up tab")
    public void signUpTab() {
        logger.info("Going to Sign Up page..");
        driver.findElement(By.xpath("(//li[@data-tab = 'register'])[1]")).click();
        take_screenshot();
    }
    @Then("User enters {string} as Email")
    public void enterEmail(String email) {
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_email'])[1]")).sendKeys(email);
        logger.info("Email address: "+email);

    }
    @Then("User enters {string} as First Name {string} as Last Name")
    public void enterNames(String fName, String lName) {
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_fname'])[1]")).sendKeys(fName);
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_lname'])[1]")).sendKeys(lName);
        logger.info("First name and Last name: "+ fName +" "+ lName);
    }
    @Then("User enters {string} as Password")
    public void enetrPassword(String passWord) {
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_pass'])[1]")).sendKeys(passWord);
    }
    @Then("User confirms {string} as Password")
    public void confirmPassword(String confirmPass) {
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_pass_again'])[1]")).sendKeys(confirmPass);
    }
    @Then("User click on Privacy Policy checkbox")
    public void clickCheckbox() {
        driver.findElement(By.xpath("(//input[@name = 'xoo_el_reg_terms'])[1]")).click();
    }
    @Then("User click on Sign Up button")
    public void clickSignup() {
        driver.findElement(By.xpath("(//button[(text() ='Sign Up')])[1]")).submit();
        WebElement ele = driver.findElement(By.xpath("(//p[contains(text(),'Hello')])[1]"));
        String innerHtml = ele.getAttribute("innerHTML");
        String homePageHeading = driver.findElement(By.xpath("(//span[text()='Dashboard'])[1]")).getText();
        logger.info("Sign Up is successful.."+ innerHtml);
        Assert.assertEquals(homePageHeading, "Dashboard");
        take_screenshot();
    }
    @After
    public void teardown() {
        logger.info("Closing browser..");
        driver.quit();
    }
}
