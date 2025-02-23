package TestLogoutFunctionality;

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

public class TestLogoutFunctionality {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/Desktop/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "http://localhost:5173/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    void testLogout() throws InterruptedException {
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

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[3]/a")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/main/div/div/div/div[1]/form/div[3]/button[1]")).click();

        Thread.sleep(2000);

        Long localStorageSize = Long.parseLong(js.executeScript("return window.localStorage.length;").toString());

        assertEquals(0, localStorageSize);

        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
