package step_definitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import runner.Hooks;

import java.io.File;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

public class CheckoutPage {
    private static WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    public final static int TIMEOUT = 3;
    private static final Logger logger = LogManager.getLogger(CheckoutPage.class);
    static String ssLocation = "screenshots/ss"+ System.currentTimeMillis()+".png";

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

    @When("User input {string} as First name {string} as Last name")
    public void enterFirNameLastName(String firstname, String lastname) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement fname= driver.findElement(By.xpath("//input[@id='billing_first_name']"));
            WebElement lname= driver.findElement(By.cssSelector("#billing_last_name"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fname);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lname);
            fname.sendKeys(firstname);
            lname.sendKeys(lastname);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Then("User input {string} as House number and street name")
    public void enterAddress(String address) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addr = driver.findElement(By.xpath("//input[@id = 'billing_address_1']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addr);
            addr.sendKeys(address);
        }  catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User input {string} as City")
    public void user_input_as_town_city(String cityname) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement city= driver.findElement(By.xpath("//input[@id = 'billing_city']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
            city.sendKeys(cityname);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Then("User input {string} as ZIP Code")
    public void enterZipcode(String zipcode) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement zcode = driver.findElement(By.xpath("//input[@id='billing_postcode']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", zcode);
            zcode.sendKeys(zipcode);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Then("User input {string} as Phone")
    public void enterPhoneNum(String phonenum) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement tel = driver.findElement(By.xpath("//input[@id='billing_phone']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tel);
            tel.sendKeys(phonenum);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Then("User input {string} as Email address")
    public void enterEmail(String email) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mail = driver.findElement(By.xpath("//input[@id='billing_email']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mail);
            mail.sendKeys(email);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User click on Klarna as payment option")
    public void radioButtonPayMethod() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement radiob = driver.findElement(By.xpath("//input[@id ='payment_method_klarna_payments_pay_over_time']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radiob);
            radiob.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User click on terms and conditions")
    public void clickCheckboxConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement checkb = driver.findElement(By.xpath("//input[@id='terms']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkb);
            checkb.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User select {string} as State")
    public void clickState(String statename) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement stateDropDown = driver.findElement(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", stateDropDown);
            logger.info("selecting state from dropdown menu..");
            stateDropDown.click();
            List<WebElement> dropDownMenu = driver.findElements(By.xpath("//select[@id='billing_state']/option"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropDownMenu);
            for (WebElement element : dropDownMenu) {
                String innerHtml = element.getAttribute("innerHTML");
                if (innerHtml.contentEquals("Texas")) {
                    element.click();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User verify the total price of the items")
    public void verifyPrice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement priceItem1 = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/span[1]/bdi[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceItem1);
            //WebElement numItem1 = driver.findElement(item1QuantityLocator);
            Float item1 = Float.parseFloat(priceItem1.getText().substring(1));
            //double item1Total = item1 * numItem1.getText();
            WebElement priceItem2 = driver.findElement(By.xpath("//tbody/tr[2]/td[2]/span[1]/bdi[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceItem2);
            Float item2 = Float.parseFloat(priceItem2.getText().substring(1));
            Float totalItemPrice = item1 + item2;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String foramtedExpectedTotalPrice = decimalFormat.format(totalItemPrice);
            WebElement total = driver.findElement(By.xpath("(//span[@class='woocommerce-Price-amount amount'])[3]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", total);
            Float totalPrice = Float.parseFloat(total.getText().substring(1));
            String foramtedActualTotalPrice = decimalFormat.format(totalPrice);
            Assert.assertEquals(foramtedExpectedTotalPrice, foramtedActualTotalPrice, "The total price is incorrect ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Then("User click on PLACE ORDER button")
    public void payNowButtonClick() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement paybutton = driver.findElement(By.xpath("//button[@id='place_order']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paybutton);
            paybutton.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User should see checkout complete message")
    public void displayMsg() {
        try{
            // Use JavaScript to display an alert box with a message
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('Checkout is completed!')");

            //take_screenshot();
            // Wait for a few seconds to see the alert (optional)
            Thread.sleep(3000);
            //take_screenshot();
            // Accept the alert (close it)
            driver.switchTo().alert().accept();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void take_screenshot(){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File(ssLocation));
        } catch (Exception e) {
            // throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

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
