package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF703removeItemTest {

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

        Thread.sleep(2000);

        WebElement showHistory = driver.findElement(By.xpath("//button[@class='show-copies-btn btn--small btn btn--primary']"));
        showHistory.click();

        Thread.sleep(2000);

        WebElement remove = driver.findElement(By.xpath("//button[@class='remove-btn btn--small btn btn--error']"));
        remove.click();


    }
}


