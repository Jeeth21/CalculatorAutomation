
import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        // Headless mode for GitHub Actions and Docker
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        String chromeBinary = System.getenv("CHROME_BIN");
        if (chromeBinary != null && !chromeBinary.isEmpty()) {
            options.setBinary(chromeBinary);
        }

        driver = new ChromeDriver(options);

        String page = new File("calculator/index.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        driver.get(page);
    }

    @Test
    public void additionTest() {

        driver.findElement(By.id("num1")).sendKeys("20");
        driver.findElement(By.id("num2")).sendKeys("10");

        driver.findElement(By.id("add")).click();

        String result = driver.findElement(By.id("result")).getText();

        assertEquals("30", result);

        System.out.println("Addition Result: " + result);
    }

    @Test
    public void subtractionTest() {

        driver.findElement(By.id("num1")).sendKeys("20");
        driver.findElement(By.id("num2")).sendKeys("10");

        driver.findElement(By.id("subtract")).click();

        String result = driver.findElement(By.id("result")).getText();

        assertEquals("10", result);

        System.out.println("Subtraction Result: " + result);
    }

    @Test
    public void multiplicationTest() {

        driver.findElement(By.id("num1")).sendKeys("20");
        driver.findElement(By.id("num2")).sendKeys("10");

        driver.findElement(By.id("multiply")).click();

        String result = driver.findElement(By.id("result")).getText();

        assertEquals("200", result);

        System.out.println("Multiplication Result: " + result);
    }

    @Test
    public void divisionTest() {

        driver.findElement(By.id("num1")).sendKeys("20");
        driver.findElement(By.id("num2")).sendKeys("10");

        driver.findElement(By.id("divide")).click();

        String result = driver.findElement(By.id("result")).getText();

        assertEquals("2", result);

        System.out.println("Division Result: " + result);
    }

    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
