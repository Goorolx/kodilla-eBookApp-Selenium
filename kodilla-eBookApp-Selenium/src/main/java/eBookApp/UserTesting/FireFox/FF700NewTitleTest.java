package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF700NewTitleTest {

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

        Thread.sleep(3000);

        WebElement addNew = driver.findElement(By.xpath("//*[@name='add-title-button']"));
        addNew.click();

        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Lord Of The Rings");

        driver.findElement(By.xpath("//input[@name='author']")).sendKeys("J.R.R. Tolkien");

        driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1947");

        WebElement addTitle = driver.findElement(By.xpath("//*[@name='submit-button']"));
        addTitle.click();

        Thread.sleep(2000);
        driver.close();
    }
}


