package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FF601NewTitleTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c://selenium-drivers/Firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");

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
        Thread.sleep(1000);

        WebElement addNew = driver.findElement(By.xpath("//*[@name='add-title-button']"));
        addNew.click();

        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Wojna i Pok\u00F3j");

        driver.findElement(By.xpath("//input[@name='author']")).sendKeys("Lew To\u0142stoj");

        driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1869");

        WebElement addTitle = driver.findElement(By.xpath("//*[@name='submit-button']"));
        addTitle.click();
        //driver.close();
    }
}


