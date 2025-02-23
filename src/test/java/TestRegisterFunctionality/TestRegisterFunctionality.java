package TestRegisterFunctionality;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRegisterFunctionality {
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
    @Order(1)
    void registerWithAllFieldsFilledCorrectly() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement registerButtonOnHome = webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[3]/a[2]"));
        registerButtonOnHome.click();

        Thread.sleep(2000);

        WebElement nameField = webDriver.findElement(By.id("name"));
        WebElement emailField = webDriver.findElement(By.id("email"));
        WebElement passwordField = webDriver.findElement(By.id("password"));
        WebElement confirmPasswordField = webDriver.findElement(By.id("confirm-password"));

        nameField.sendKeys("Salih Rogo");
        Thread.sleep(1500);
        emailField.sendKeys("testemail1@gmail.com");
        Thread.sleep(1500);
        passwordField.sendKeys("test1234");
        Thread.sleep(1500);
        confirmPasswordField.sendKeys("test1234");

        Thread.sleep(2000);

        WebElement register = webDriver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div/div/form/button"));
        register.click();

        Thread.sleep(3000);

        String urlAfterRegister = webDriver.getCurrentUrl();

        assertEquals("http://localhost:5173/profile", urlAfterRegister);

        Thread.sleep(2000);

        js.executeScript("window.localStorage.clear();");
    }

    @Test
    @Order(2)
    void registerWithEmptyFields() throws InterruptedException  {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement registerButtonOnHome = webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[3]/a[2]"));
        registerButtonOnHome.click();

        Thread.sleep(2000);

        WebElement register = webDriver.findElement(By.xpath("//*[@id=\"root\"]/section/div/div/div/form/button"));
        register.click();

        Thread.sleep(3000);

        webDriver.switchTo().alert().accept();

        String urlAfterRegister = webDriver.getCurrentUrl();

        assertEquals("http://localhost:5173/register", urlAfterRegister);

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
