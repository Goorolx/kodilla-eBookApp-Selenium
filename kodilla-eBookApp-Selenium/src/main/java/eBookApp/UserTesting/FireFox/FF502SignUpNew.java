package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FF502SignUpNew {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c://selenium-drivers/Firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/register");

//        while (!driver.findElement(By.xpath("//*[@class='container']")).isDisplayed()) ;
//        {
//        }
//
//        driver.findElement(By.xpath("//a[@href='/register']")).click();

//        while (!driver.findElement(By.xpath("//a[@href='/login']")).isDisplayed()) ;
//        {
//        }
//        WebDriverWait w = new WebDriverWait(driver,30);
//        w.wait(1000);



        WebElement log = driver.findElement(By.xpath("//*[@id='login']"));
        //WebElement log = driver.findElement(By.xpath("//input[1]"));
        log.sendKeys("Zbigniew Tester");

        WebElement pwd = driver.findElement(By.xpath("//*[@id='password']"));
        String p = "rudy102";
        pwd.sendKeys(p);

        WebElement pwdRep = driver.findElement(By.xpath("//*[@id='password-repeat']"));
        pwdRep.sendKeys(p);

        WebElement signup = driver.findElement(By.id("register-btn"));
        signup.click();
    }
}

