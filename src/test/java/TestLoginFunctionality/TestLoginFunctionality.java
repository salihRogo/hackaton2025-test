package TestLoginFunctionality;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoginFunctionality {
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
    @Order(1)
    void loginWithCorrectCredentials() throws InterruptedException {
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

        String urlAfterLogin = webDriver.getCurrentUrl();

        assertEquals("http://localhost:5173/donate", urlAfterLogin);

        Thread.sleep(2000);

        js.executeScript("window.localStorage.clear();");
    }

    @Test
    @Order(2)
    void loginWithIncorrectCredentials() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement loginButtonOnHome = webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[3]/a[1]"));
        loginButtonOnHome.click();

        WebElement emailInput = webDriver.findElement(By.id("email"));
        WebElement passwordInput = webDriver.findElement(By.id("password"));

        emailInput.sendKeys("eeeeemmmmmaemir@gmail.com");
        Thread.sleep(2000);
        passwordInput.sendKeys("test1268147908334");

        Thread.sleep(3000);

        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div/div/form/button"));
        login.click();

        Thread.sleep(2000);

        String urlAfterLogin = webDriver.getCurrentUrl();

        assertEquals("http://localhost:5173/login", urlAfterLogin);

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
