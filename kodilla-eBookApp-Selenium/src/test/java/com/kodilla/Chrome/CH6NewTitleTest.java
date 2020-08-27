package com.kodilla.Chrome;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CH6NewTitleTest{

    @Test
    public void checkAssertions() throws InterruptedException {
        //given
        System.setProperty("webdriver.chrome.driver", "c://selenium-drivers/chrome/chromedriver83.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

        WebElement login = driver.findElement(By.id("login"));
        login.sendKeys("Zbigniew Tester");

        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("rudy103");

        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();

        String ActualTitle = driver.getTitle();
        System.out.println(ActualTitle);

        Assertions.assertEquals(ActualTitle,"app");

        driver.close();

    }}
