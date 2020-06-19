package eBookApp.UserTesting.Chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Ch601NewTitleTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "c://selenium-drivers/chrome/chromedriver83.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");

        Thread.sleep(2000);

        WebElement login = driver.findElement(By.id("login"));
        login.sendKeys("Zbigniew Tester");

        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("rudy102");

        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();

//            WebDriverWait wait = new WebDriverWait(driver, 30);
//            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-title-button")));
//
//            while (!driver.findElement(By.xpath("//*[@name='add-title-button']")).isDisplayed()) ;
//            {
//            }
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);

        WebElement addNew = driver.findElement(By.xpath("//*[@name='add-title-button']"));
        addNew.click();

        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Wojna i Pokój");

        driver.findElement(By.xpath("//input[@name='author']")).sendKeys("Lew Tołstoj");

        driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1869");

        WebElement addTitle = driver.findElement(By.xpath("//*[@name='submit-button']"));
        addTitle.click();
        //driver.close();


    }
}


