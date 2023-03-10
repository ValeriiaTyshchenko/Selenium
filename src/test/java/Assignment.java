import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Assignment {
//    Continue to develop tests for the Facebook Signup page
//Think about long text testing, special character testing, invalid inputs (email), etc.
// For now, don`t test the error messages, just validate that the new screen was not opened.

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

    //    On Facebook main screen find XPath and create a method to press the Create New Account button
    @BeforeEach
    public void createNewAccTest()
            throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        Thread.sleep(1000);
    }

    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);

    }

    //Find Xpathes for each text box component and Sign Up button,
    //Add JUnit tests for account creation page
    //Think about long text testing, special character testing, invalid inputs (email), etc.
    // For now, don`t test the error messages, just validate that the new screen was not opened.
    @Test
    public void creationPageTests() throws InterruptedException {
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys("valeriia");
        String firstNameValue = firstNameElement.getAttribute("value");
        assertEquals("valeriia", firstNameValue);
        assertFalse(firstNameValue.isEmpty() || firstNameValue.isBlank());
        assertFalse(firstNameValue.length() < 3 || firstNameValue.length() > 21);


        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.sendKeys("klim");
        String lastNameValue = lastNameElement.getAttribute("value");
        assertEquals("klim", lastNameValue);
        assertFalse(lastNameValue.isEmpty() || lastNameValue.isBlank());
        assertFalse(lastNameValue.length() < 3 || lastNameValue.length() > 21);

        WebElement mobileNumberOrEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(mobileNumberOrEmailElement);
        mobileNumberOrEmailElement.sendKeys("1234567890");
        String mobileNumberOrEmailValue = mobileNumberOrEmailElement.getAttribute("value");
        assertEquals("1234567890", mobileNumberOrEmailValue);
        assertFalse(mobileNumberOrEmailValue.isEmpty() || mobileNumberOrEmailValue.isBlank());
        assertFalse(mobileNumberOrEmailValue.length() < 10 || mobileNumberOrEmailValue.length() > 15);

        WebElement newPasswordElement = driver.findElement(By.xpath("//input[@autocomplete='new-password']"));
        assertNotNull(newPasswordElement);
        newPasswordElement.sendKeys("password!!!");
        String newPasswordValue = newPasswordElement.getAttribute("value");
        assertEquals("password!!!", newPasswordValue);
        assertFalse(newPasswordValue.isEmpty() || newPasswordValue.isBlank());
        assertFalse(newPasswordValue.length() < 8 || newPasswordValue.length() > 20);

        //Don't forget to choose the Custom gender field to verify new  text boxes are displayed and test these text boxes as well
        WebElement customGenderFieldElement = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[7]/span/span[3]/label"));
        assertNotNull(customGenderFieldElement);
        customGenderFieldElement.click();
        Thread.sleep(1000);
        WebElement genderFieldElement = driver.findElement(By.xpath("//input[@name='custom_gender']"));
        assertNotNull(genderFieldElement);
        genderFieldElement.sendKeys("Non-Conforming");
        String genderFieldValue = genderFieldElement.getAttribute("value");
        assertEquals("Non-Conforming", genderFieldValue);


        WebElement signUpButtonElements = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButtonElements);
        signUpButtonElements.click();


    }

    @Test
    public void policiesButtonsTest() {
        WebElement leanMoreLinkElements = driver.findElement(By.xpath("//a[@id='non-users-notice-link']"));
        assertNotNull(leanMoreLinkElements);
        leanMoreLinkElements.click();

        WebElement termsLinkElements = driver.findElement(By.xpath("//a[@id='terms-link']"));
        assertNotNull(termsLinkElements);
        termsLinkElements.click();

        WebElement privacyPolicyLinkElements = driver.findElement(By.xpath("//a[@id='privacy-link']"));
        assertNotNull(privacyPolicyLinkElements);
        privacyPolicyLinkElements.click();

        WebElement cookiesPolicyLinkElements = driver.findElement(By.xpath("//a[@id='cookie-use-link']"));
        assertNotNull(cookiesPolicyLinkElements);
        cookiesPolicyLinkElements.click();
    }


}
