package eBookApp.UserTesting.Chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestingChrome501 {

        public static void main(String[] args) {
            System.setProperty("webdriver.chrome.driver", "c://selenium-drivers/chrome/chromedriver83.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://ta-ebookrental-fe.herokuapp.com/");

            WebElement login = driver.findElement(By.xpath("//*[@class='container']/form/div/label/input"));
            //WebElement login = driver.findElement(By.xpath("//input[1]"));
            login.sendKeys("goorolx");

            WebElement pwd = driver.findElement(By.xpath("//input[2]"));
            pwd.sendKeys("rudy103");

//            WebElement button = driver.findElement(By.id("login-btn"));
//            button.click();
        }
    }


