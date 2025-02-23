package TestFiltersOnLeaderboard;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFiltersOnLeaderboard {
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
    void defaultViewShowsAllDonations() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[2]/a[5]")).click();

        Thread.sleep(1000);

        int i = 1;
        while (true) {
            try {
                WebElement checkElement = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[" + i + "]/span"));
                assertTrue(checkElement.isDisplayed());
                i++;
                js.executeScript("scrollBy(0, 100)");
                Thread.sleep(400);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    void treesSelectedShowsDonationsForTrees() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[2]/a[5]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/button[2]")).click();

        Thread.sleep(2000);

        int i = 1;
        while (true) {
            try {
                WebElement checkElement = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[" + i + "]/span"));
                assertTrue(checkElement.getText().startsWith("Posadio/la"));
                i++;
                js.executeScript("scrollBy(0, 100)");
                Thread.sleep(400);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Test
    @Order(3)
    void riverSelectedShowsDonationsForRivers() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[2]/a[5]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/button[1]")).click();

        Thread.sleep(2000);

        int i = 1;
        while (true) {
            try {
                WebElement checkElement = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[" + i + "]/span"));
                assertTrue(checkElement.getText().startsWith("Očistio/la"));
                i++;
                js.executeScript("scrollBy(0, 100)");
                Thread.sleep(400);
            } catch (NoSuchElementException e) {
                break;
            }
        }
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
