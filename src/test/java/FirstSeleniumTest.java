import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();

    }


    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);

    }

    @Test
    public void homePageURLTest() {

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs do not match");

    }

    @Test
    public void findEmailFieldTest() {
//        WebElement element = driver.findElement(By.id("email"));
//        WebElement element = driver.findElement(By.name("email"));
//        WebElement element = driver.findElement(By.linkText("Create a Page"));
//        WebElement element = driver.findElement(By.linkText("a Page"));
//         WebElement element = driver.findElement(By.cssSelector("#email"));
        List<WebElement> element = driver.findElements(By.className("inputtext"));
        System.out.println(element.size());

        assertNotNull(element);
    }

    @Test
    public void findElementByXpathTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
//        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
//        assertNotNull(loginButtonElement);
//        WebElement forgotPasswordElement = driver.findElement(By.xpath("//*[text()='Forgot password?']"));
//        assertNotNull(forgotPasswordElement);
        WebElement createNewAccButtonElement = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(createNewAccButtonElement);

    }

    @Test
    public void loginScreenTest() {
        //find email field
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        //validate that it exist
        assertNotNull(emailElement);
        //send input to the email field
        emailElement.sendKeys("klim.ivanov1977@gmail.com");
        //get value from email field
        String emailValue = emailElement.getAttribute("value");
        //compare input with expected result
        assertEquals("klim.ivanov1977@gmail.com", emailValue);
        //find password field
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        //validate that it exist
        assertNotNull(passwordElement);
        //send input to the password field
        passwordElement.sendKeys("Olexiy-02-12-80!");
        //get value from password field
        String passValue = passwordElement.getAttribute("value");
        //compare input with expected result
        assertEquals("Olexiy-02-12-80!", passValue);

        //find login button
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);

        //click on login button
        loginButtonElement.click();
    }

    @Test
    public void sighupTest() {

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));


    }

    @Test
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);
// verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

// verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);
    }

    @Test
    public void errorMessageTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'to reset')]"));
        assertNotNull(error);

    }

    @Test
    public void yearTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() ='1990']")).click();

        String yearValue =   driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals("1990", yearValue);




    }

    @ParameterizedTest
    @ValueSource (strings = {"1905", "1975", "2023"})
    public void yearTestParametrized(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() ='"+ yearInput +"']")).click();

        String yearValue =   driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals(yearInput, yearValue);


        }
    @Test
    public void actionTest()  {
        driver.get("https://daviktapes.com/");
//        pause();
//        wait for maximum 5 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Company']")));

        //ждем пока элемент не исчезнет
        wait.until(ExpectedConditions.invisibilityOf((WebElement) By.xpath("//a[text() = 'Company']")));

        WebElement element = driver.findElement(By.xpath("//a[text() = 'Company']"));
// to hover mouse over the button
        Actions actions = new Actions(driver);

        actions.moveToElement(element).build().perform();



    }
    @Test
    public void waitAndClickTest()  {
        driver.get("https://daviktapes.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text() = 'Company']"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Company']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Company']"))).click();
        pause();
    }



     public void pause(){
     try {Thread.sleep(5000);

     } catch (Exception err){
         System.out.println("Something went wrong");
     }

    }

    public void smallPause(){
        try {Thread.sleep(1000);

        } catch (Exception err){
            System.out.println("Something went wrong");
        }

    }
    public void largePause(){
        try {Thread.sleep(10000);

        } catch (Exception err){
            System.out.println("Something went wrong");
        }

    }
}
