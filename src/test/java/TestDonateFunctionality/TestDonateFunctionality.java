package TestDonateFunctionality;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDonateFunctionality {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "path-to-downloaded-chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "http://localhost:5173/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    void testDonateFlowWithCreatedAccount() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement loginButtonOnHome = webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[3]/a[1]"));
        loginButtonOnHome.click();

        WebElement emailInput = webDriver.findElement(By.id("email"));
        WebElement passwordInput = webDriver.findElement(By.id("password"));

        emailInput.sendKeys("eemaemir@gmail.com");
        Thread.sleep(2000);
        passwordInput.sendKeys("test1234");

        Thread.sleep(3000);

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div/div/form/button"));
        login.click();

        Thread.sleep(3000);

        WebElement selectRiverCleanUp = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]"));
        WebElement selectAmount = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/button[3]"));

        selectRiverCleanUp.click();
        Thread.sleep(1500);
        selectAmount.click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/button")).click();

        Thread.sleep(7000);

        assertTrue(webDriver.getCurrentUrl().startsWith("https://checkout.stripe.com/"));

        WebElement cardNumberInput = webDriver.findElement(By.id("cardNumber"));
        WebElement cardDateInput = webDriver.findElement(By.id("cardExpiry"));
        WebElement cardCvcInput = webDriver.findElement(By.id("cardCvc"));
        WebElement cardNameInput = webDriver.findElement(By.id("billingName"));

        cardNumberInput.sendKeys("4242424242424242");
        Thread.sleep(1500);
        cardDateInput.sendKeys("1125");
        Thread.sleep(1500);
        cardCvcInput.sendKeys("999");
        Thread.sleep(1500);
        cardNameInput.sendKeys("Emir Prašović");

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/main/div/form/div[1]/div/div/div[3]/div/div[3]/button")).click();

        Thread.sleep(5000);

        assertEquals("http://localhost:5173/leaderboard", webDriver.getCurrentUrl());

        WebElement nameOnRanking = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/div/span[2]"));
        assertEquals("Emir DOO", nameOnRanking.getText());

        Thread.sleep(2000);
        js.executeScript("window.localStorage.clear();");
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
