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

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumExtension.class)
public class CH60xTitlesTestSuite {
    private String logingx = "goorolx";
    private String pwdgx = "rudy103";
    private String title = "War and Peace";
    private String author = "Lew Tolstoj";
    private Integer year = 1869;


    @Test
    public void t501shouldAdd2NewTitle(ChromeDriver driver) {
        //given
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

        //log in to system, I should get to titles page
        WebElement log = driver.findElement(By.id("login"));
        log.sendKeys("goorolx");
        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("rudy103");
        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();

        //when
        //Adding new Title
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait below not always worked
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        WebElement addTitle = driver.findElement(By.cssSelector("#add-title-button"));
        addTitle.click();

        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(title);
        driver.findElement(By.xpath("//input[@name='author']")).sendKeys(author);
        driver.findElement(By.xpath("//input[@name='year']")).sendKeys(year.toString());

        driver.findElement(By.xpath("//*[@name='submit-button']")).click();

        //Adding second title
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait below not always worked
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-title-button")));
        WebElement addTitle2 = driver.findElement(By.cssSelector("#add-title-button"));
        addTitle2.click();

        Integer newYear = year + 2;
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(title + 2);
        driver.findElement(By.xpath("//input[@name='author']")).sendKeys(author);
        driver.findElement(By.xpath("//input[@name='year']")).sendKeys(newYear.toString());

        addTitle2 = driver.findElement(By.xpath("//*[@name='submit-button']"));
        addTitle2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        List<WebElement> listTitles = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        List<WebElement> listAuthor = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[2]");
        List<WebElement> listYear = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[3]");
        //then
        Assertions.assertEquals(4,listTitles.size()); // List should contain 2 elements

        for (int i = 0; i < listTitles.size(); i++)   // checking if titles are added correctly
            if(i%2==0) Assertions.assertTrue((title).equalsIgnoreCase(listTitles.get(i).getText()));
            else Assertions.assertTrue((title+2).equalsIgnoreCase((listTitles.get(i).getText())));

        for (int i=0;i<listAuthor.size();i++) { // checking if author is corect
           Assertions.assertTrue(("by "+author).equalsIgnoreCase(listAuthor.get(i).getText()));}

        for (int i=0;i<listYear.size();i++)  //Checking if year is correct
            if(i%2==0) Assertions.assertEquals(("("+year+")"),listYear.get(i).getText());
            else Assertions.assertEquals(("("+newYear+")"),listYear.get(i).getText());

    }
}
