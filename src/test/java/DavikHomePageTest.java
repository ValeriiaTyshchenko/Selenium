import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DavikHomePageTest {

//    navigate to ( you can also use driver.navigate.to("") method) https://www.daviktapes.com/

    private static final String HOME_PAGE_URL = "https://www.daviktapes.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.navigate().to(HOME_PAGE_URL);

    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();

    }


//    Using actions class, move the mouse to every top menu option and verify it`s opened in each case.
//Use Explicit wait to check each menu had enough time to be expanded ( you may use the presenceOfElementLocated method with one of the options element XPath).
    @Test
    public void actionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Company']")));
        WebElement companyElement = driver.findElement(By.xpath("//a[text() = 'Company']"));
        actions.moveToElement(companyElement).build().perform();
        WebElement popupCompany = driver.findElement(By.xpath("//*[@id = 'menu-item-2063']"));
        assertNotNull(popupCompany);


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Products']")));
        WebElement productsElement = driver.findElement(By.xpath("//a[text() = 'Products']"));
        actions.moveToElement(productsElement).build().perform();
        WebElement popupProducts = driver.findElement(By.xpath("//*[@id = 'menu-item-2069']"));
        assertNotNull(popupProducts);


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Industries']")));
        WebElement industriesElement = driver.findElement(By.xpath("//a[text() = 'Industries']"));
        actions.moveToElement(industriesElement).build().perform();
        WebElement popupIndustries = driver.findElement(By.xpath("//*[@id = 'menu-item-1428']"));
        assertNotNull(popupIndustries);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Knowledge Center']")));
        WebElement knowledgeCenterElement = driver.findElement(By.xpath("//a[text() = 'Knowledge Center']"));
        actions.moveToElement(knowledgeCenterElement).build().perform();
        WebElement popupKnowledgeCenter = driver.findElement(By.xpath("//*[@id = 'menu-item-2082']"));
        assertNotNull(popupKnowledgeCenter);


    }

}
