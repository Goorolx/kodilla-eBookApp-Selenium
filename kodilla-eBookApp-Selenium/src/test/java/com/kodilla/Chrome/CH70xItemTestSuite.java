package com.kodilla.Chrome;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.testng.annotations.Test;


//@ExtendWith(SeleniumExtension.class)
public class CH70xItemTestSuite {

    private String logingx = "goorolx";
    private String pwdgx = "rudy103";


    @Test
    public void t701shouldAddItemCorrect(){

        System.setProperty("webdriver.chrome.driver", "c://selenium-drivers/chrome/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
//        goToItems(driver);
//
//    }
//
//    public void goToItems(ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

        //log in to system, I should get to titles page
        WebElement log = driver.findElement(By.id("login"));
        log.sendKeys(logingx);
        WebElement pwd = driver.findElement(By.id("password"));
        pwd.sendKeys(pwdgx);
        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));


    }
}
