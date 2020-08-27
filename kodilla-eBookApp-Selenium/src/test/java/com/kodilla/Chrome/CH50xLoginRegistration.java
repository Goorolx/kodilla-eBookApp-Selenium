package com.kodilla.Chrome;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class CH50xLoginRegistration {

    private String login = "Zbigniew Tester";
    private String password = "rudy103";

    @Test
    public void testLocalChrome(ChromeDriver driver) {
        // use local Chrome in this test
        driver.get("https://interia.pl/");
        Assertions.assertFalse(driver.getTitle().isEmpty());
    }

    @Test
    public void t501shouldLoginSuccessful(ChromeDriver driver) throws InterruptedException {
        //given

        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));
        //when
        WebElement login = driver.findElement(By.id("login"));
        login.sendKeys("goorolx");
        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("rudy103");
        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();

        //then
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        Assertions.assertTrue(driver.findElement(By.id("titles")).isDisplayed());
    }

    @Test
    public void shouldRegisterNewUser(ChromeDriver driver) {
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

        WebElement signUp = driver.findElementById("register-btn");
        signUp.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        WebElement log = driver.findElementById("login");
        log.sendKeys(login);
        WebElement pwd = driver.findElementByCssSelector("#password"); //CSS selectors are cool :)
        pwd.sendKeys(password);
        WebElement pwd2 = driver.findElementByCssSelector("#password-repeat");
        pwd2.sendKeys(password);

        driver.findElement(By.id("register-btn")).click();
        //then
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#titles")));
        Assertions.assertTrue(driver.findElement(By.id("titles")).isDisplayed());
    }

    @Test
    private void tryLoginWithWrongUserName(ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));
        //when
        WebElement login = driver.findElement(By.id("login"));
        login.sendKeys("goorolx");
        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("rudy103");
        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();

        //then
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        Assertions.assertTrue(driver.findElement(By.id("titles")).isDisplayed());
    }

}




