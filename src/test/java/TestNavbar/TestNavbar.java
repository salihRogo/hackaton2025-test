package TestNavbar;

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

public class TestNavbar {
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
    void testNavbar() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(4000);

        String[] links = {
                "about", "donate", "merch", "leaderboard", "login", "register"
        };

        String[] xpaths = {
                "//*[@id=\"root\"]/header/div/nav/div[2]/a[2]",
                "//*[@id=\"root\"]/header/div/nav/div[2]/a[3]",
                "//*[@id=\"root\"]/header/div/nav/div[2]/a[4]",
                "//*[@id=\"root\"]/header/div/nav/div[2]/a[5]",
                "//*[@id=\"root\"]/header/div/nav/div[3]/a[1]",
                "//*[@id=\"root\"]/header/div/nav/div[3]/a[2]"
        };


        for (int i = 0; i < links.length; i++) {
            String url = baseUrl + links[i];
            webDriver.findElement(By.xpath(xpaths[i])).click();
            Thread.sleep(1500);
            i++;
            assertEquals(url, webDriver.getCurrentUrl());
            Thread.sleep(1500);
        }

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/header/div/nav/div[2]/a[1]")).click();
        assertEquals("http://localhost:5173/", webDriver.getCurrentUrl());

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
