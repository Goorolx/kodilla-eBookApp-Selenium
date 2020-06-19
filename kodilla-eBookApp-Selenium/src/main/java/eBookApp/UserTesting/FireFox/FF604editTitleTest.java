package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF604editTitleTest {

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
        Thread.sleep(2000);

        WebElement addNew = driver.findElement(By.xpath("//*[@class='edit-btn btn--small btn btn--warning']"));
        addNew.click();

        WebElement title =  driver.findElement(By.xpath("//input[@name='title']"));
        title.clear();
        title.sendKeys("Wojna i Pokoje");

        WebElement author = driver.findElement(By.xpath("//input[@name='author']"));
        author.clear();
        author.sendKeys("Lew Tolstojewski");

        WebElement year = driver.findElement(By.xpath("//input[@name='year']"));
        year.clear();
        year.sendKeys("1888");

        WebElement addTitle = driver.findElement(By.xpath("//*[@name='submit-button']"));
        addTitle.click();

        //driver.close();
    }
}


