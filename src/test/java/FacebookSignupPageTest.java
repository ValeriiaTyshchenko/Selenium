import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacebookSignupPageTest {

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

    @BeforeEach
    public void createNewAccTest()
            throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);
    }

    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);

    }


    // 1. Verify all the errors messages for empty fields
    @Test
    public void errorMessageTest() {

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();

        driver.findElement(By.xpath("//input[@name='firstname']")).click();
       String firstNameError = driver.findElement(By.xpath("//div[contains(text(), 'name?')]")).getText();
        assertEquals("What's your name?", firstNameError);

        driver.findElement(By.xpath("//input[@name='lastname']")).click();
        WebElement lastNameError = driver.findElement(By.xpath("//*[@class='_5633 _5634 _53ij']"));
        assertNotNull(lastNameError);
        String lastError = lastNameError.getText();
        assertEquals("What's your name?", lastError);

        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        WebElement emailError = driver.findElement(By.xpath("//*[contains(text(),'to reset')]"));
        assertNotNull(emailError);

        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).click();
        WebElement newPasswordError = driver.findElement(By.xpath("//*[contains(text(),'six numbers')]"));
        assertNotNull(newPasswordError);


    }


    // 2. Test Months droplist
    @ParameterizedTest
    @CsvSource({"1,Jan", "2, Feb", "3, Mar", "4, Apr", "5, May", "6, Jun", "7, Jul", "8, Aug", "9, Sep", "10, Oct", "11, Nov", "12, Dec"})
    public void monthsTestParametrized(String monthNumber, String monthInput) {

        driver.findElement(By.xpath("//*[@title='Month']")).click();
        driver.findElement(By.xpath("//*[text() ='" + monthInput + "']")).click();

        String monthValue = driver.findElement(By.xpath("//*[@title='Month']")).getAttribute("value");

        assertEquals(monthNumber, monthValue);

    }


    // 3. Test Years droplist
    @ParameterizedTest
    @ValueSource(strings = {"1905", "1975", "2020"})
    public void yearTestParametrized(String yearInput) {

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() ='" + yearInput + "']")).click();

        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals(yearInput, yearValue);


    }


    // 4. Find and test Radio buttons using sibling in Xpath
    @Test
    public void radioButtonsTest() {
        String femaleXpath = "//*[text() = 'Female']//following-sibling::*[@type='radio']";
        String maleXpath = "//*[text() = 'Male']//following-sibling::*[@type='radio']";
        String customXpath = "//*[text() = 'Custom']//following-sibling::*[@type='radio']";

        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

        WebElement customButton = driver.findElement(By.xpath(customXpath));
        customButton.click();
        String isCustomChecked = driver.findElement(By.xpath(customXpath)).getAttribute("checked");
        assertNotNull(isCustomChecked);
        assertEquals("true", isCustomChecked);
    }


    // 5. Test Terms and DataPolicy links, verify that the new pages are opened after pressing it*
    @Test
    public void policiesLinksTest() {

        WebElement leanMoreLinkElements = driver.findElement(By.xpath("//a[@id='non-users-notice-link']"));
        assertNotNull(leanMoreLinkElements);
        leanMoreLinkElements.click();


        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String learnMoreURL = driver.getCurrentUrl();
        assertEquals("https://www.facebook.com/help/637205020878504", learnMoreURL);


    }

    @Test
    public void termsLinkLinksTest() {


        WebElement termsLinkElements = driver.findElement(By.xpath("//a[@id='terms-link']"));
        assertNotNull(termsLinkElements);
        termsLinkElements.click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String termsURL = driver.getCurrentUrl();
        assertEquals("https://www.facebook.com/legal/terms/update", termsURL);
    }

    @Test
    public void privacyPolicyLinksTest() {
        WebElement privacyPolicyLinkElements = driver.findElement(By.xpath("//a[@id='privacy-link']"));
        assertNotNull(privacyPolicyLinkElements);
        privacyPolicyLinkElements.click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String privacyPolicyURL = driver.getCurrentUrl();
        assertEquals("https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0", privacyPolicyURL);



}
    @Test
    public void cookiesPolicyTest() {
        WebElement cookiesPolicyLinkElements = driver.findElement(By.xpath("//a[@id='cookie-use-link']"));
        assertNotNull(cookiesPolicyLinkElements);
        cookiesPolicyLinkElements.click();

        for (String str : driver.getWindowHandles()) {
        driver.switchTo().window(str);
    }
    String cookiesPolicyURL = driver.getCurrentUrl();

    assertEquals("https://www.facebook.com/privacy/policies/cookies/?entry_point=cookie_policy_redirect&entry=0", cookiesPolicyURL);



    }
}
