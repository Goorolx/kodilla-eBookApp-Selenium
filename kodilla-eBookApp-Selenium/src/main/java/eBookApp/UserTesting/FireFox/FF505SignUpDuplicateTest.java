package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF505SignUpDuplicateTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c://selenium-drivers/Firefox/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/register");


        WebElement log = driver.findElement(By.xpath("//*[@id='login']"));
        //WebElement log = driver.findElement(By.xpath("//input[1]"));
        log.sendKeys("Zbigniew Tester");

        WebElement pwd = driver.findElement(By.xpath("//*[@id='password']"));
        String p = "rudy102";
        pwd.sendKeys(p);

        WebElement pwdRep = driver.findElement(By.xpath("//*[@id='password-repeat']"));
        pwdRep.sendKeys(p);


    }
}

