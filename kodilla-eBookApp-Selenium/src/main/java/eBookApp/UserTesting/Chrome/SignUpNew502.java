package eBookApp.UserTesting.Chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpNew502 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "c://selenium-drivers/chrome/chromedriver83.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");

        //driver.findElement(By.xpath("//*[@class=\"flex\"]/button")).click();
        driver.findElement(By.xpath("//a[@href='/register']")).click();

        while (!driver.findElement(By.id("password")).isDisplayed());
        {
        }
        // WebDriverWait wait = new WebDriverWait(driver,30);
       // wait.until(ExpectedConditions.visibilityOf());
       // wait.until(ExpectedConditions.visibilityOf(By.name("id=\"password-repeat\"")));

        WebElement log = driver.findElement(By.id("login"));
        log.sendKeys("Zbigniew Tester");

        WebElement pwd = driver.findElement(By.name("password"));
        String p = "rudy102";
        pwd.sendKeys(p);

        WebElement pwdRep = driver.findElement(By.name("password-repeat"));
        pwdRep.sendKeys(p);

        WebElement signup = driver.findElement(By.id("register-btn"));
        signup.click();
}}

