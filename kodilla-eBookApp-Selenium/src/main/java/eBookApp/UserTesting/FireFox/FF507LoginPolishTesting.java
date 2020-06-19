package eBookApp.UserTesting.FireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FF507LoginPolishTesting {

        public static void main(String[] args) {
            System.setProperty("webdriver.gecko.driver", "c://selenium-drivers/Firefox/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.get("https://ta-ebookrental-fe.herokuapp.com/");

            WebElement login = driver.findElement(By.id("login"));
            login.sendKeys("Kińżę Łóśćą");

            WebElement pwd = driver.findElement(By.name("password"));
            pwd.sendKeys("ródą102");

            WebElement button = driver.findElement(By.id("login-btn"));
            button.click();
        }
    }


