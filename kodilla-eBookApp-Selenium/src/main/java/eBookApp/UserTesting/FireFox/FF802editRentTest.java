package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF802editRentTest {

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

        WebElement goRent = driver.findElement(By.xpath("//button[@class='show-rents-btn btn--small btn btn--primary']"));
        goRent.click();
        Thread.sleep(2000);

        WebElement newRent = driver.findElement(By.xpath("//button[@class='edit-btn btn--small btn btn--warning']"));
        newRent.click();

        WebElement name = driver.findElement(By.xpath("//input[@name='customer-name']"));
        name.clear();
        name.sendKeys("TesterSka");

        WebElement rent = driver.findElement(By.xpath("//input[@name=\"rent-date\"]"));
        rent.click();

        WebElement openCal = driver.findElement(By.xpath("//*[@class='day__month_btn up']"));
        openCal.click();
        Thread.sleep(2000);
        WebElement goUp = driver.findElement(By.xpath("//*[@class='month__year_btn up']"));
        goUp.click();
        Thread.sleep(1000);
        WebElement yearSelect = driver.findElement(By.xpath("//span[@class='cell year selected' and contains(text(), '2020')]"));
        yearSelect.click();
        Thread.sleep(1000);
        WebElement monthSelect = driver.findElement(By.xpath("//span[@class='cell month' and contains(text(), 'March')]"));
        monthSelect.click();
        Thread.sleep(1000);
        WebElement daySelect = driver.findElement(By.xpath("//span[@class='cell day' and contains(text(), '30')]"));
        daySelect.click();
        Thread.sleep(1000);

        WebElement rentEx = driver.findElement(By.xpath("//input[@name='expiration-date']"));
        rentEx.click();
        Thread.sleep(3000);
//        WebElement openCalEx = driver.findElement(By.xpath("//div[contains(@name, 'expiration-date')]//*[@class='day__month_btn up']"));
//        openCalEx.click();

        WebElement openCalEx = driver.findElement(By.xpath("//div[@class='fog__content']/form/div[3]/div/div/div[2]/header/span[2]"));
        openCalEx.click();

        Thread.sleep(2000);
        WebElement goUpEx = driver.findElement(By.xpath("//div[@class='fog__content']/form/div[3]/div/div/div[3]/header/span[2]"));
        goUpEx.click();
        Thread.sleep(2000);
        WebElement yearSelectEx = driver.findElement(By.xpath("//div[@class='fog__content']/form/div[3]/div/div/div[4]/span[1]"));
        yearSelectEx.click();
        Thread.sleep(2000);

        //month selector
        WebElement monthSelectEx = driver.findElement(By.xpath("//div[@class='fog__content']/form/div[3]/div/div/div[3]/span[6]"));
        monthSelectEx.click();
        Thread.sleep(2000);
        WebElement daySelectEx = driver.findElement(By.xpath("//div[@class='fog__content']/form/div[3]/div/div/div[2]/div/span[20]"));
        daySelectEx.click();
        Thread.sleep(2000);


        WebElement add = driver.findElement(By.xpath("//*[@name='submit-button']"));
        add.click();


    }
}


