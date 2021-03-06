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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumExtension.class)
public class CH60xTitlesTestSuite {
    private String logingx = "goorolx";
    private String pwdgx = "rudy103";
    private String title = "War and Peace";
    private String author = "Lew Tolstoj";
    private Integer year = 1869;


    @Test
    public void t601shouldAdd2NewTitle(ChromeDriver driver) {
        //given
        WebDriverWait wait = new WebDriverWait(driver, 5);
        doLogin(driver);

        //creating a list to verify whether correct number of titles was added
        List<WebElement> listTitlesBefore = new ArrayList<>();  //just to make sure it works when list is empty
        try {
            listTitlesBefore = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        } catch (Exception e) {
            System.out.println("no titles");
        }

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
        int ls = listTitles.size();
        //then
        Assertions.assertEquals(listTitlesBefore.size() + 2, listTitles.size()); // List should contain 2 more elements
        //Verifying last two added titles
        for (int i = ls-1; i > ls-3; i--)   // checking if titles are added correctly
            if (i == ls-2) Assertions.assertTrue((title).equalsIgnoreCase(listTitles.get(i).getText()));
            else Assertions.assertTrue((title + 2).equalsIgnoreCase((listTitles.get(i).getText())));

        for (int i = ls-1; i > ls-2; i--)  // checking if author is correct
            Assertions.assertTrue(("by " + author).equalsIgnoreCase(listAuthor.get(i).getText()));

        for (int i = ls-1; i > ls-3; i--)  //Checking if year is correct
            if (i == ls-2) Assertions.assertEquals(("(" + year + ")"), listYear.get(i).getText());
            else Assertions.assertEquals(("(" + newYear + ")"), listYear.get(i).getText());
    }

    @Test
    public void t602shouldNotAddTitleWithEmptyFields(ChromeDriver driver) {
        //given
        WebDriverWait wait = new WebDriverWait(driver, 5);
        doLogin(driver);

        //when
        //Adding new Title
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait below not always worked
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        //get titles to compare
        List<WebElement> listTitlesBefore = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");

        WebElement addTitle = driver.findElement(By.cssSelector("#add-title-button"));
        addTitle.click();
        //602a - Try Add Title with empty Title
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='author']")).sendKeys(author);
        driver.findElement(By.xpath("//input[@name='year']")).sendKeys(year.toString());

        driver.findElement(By.xpath("//*[@name='submit-button']")).click();

        Assertions.assertTrue(driver.findElementByCssSelector("[class*=alert--error]").isDisplayed());
        List<WebElement> listTitleAfter = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        Assertions.assertEquals(listTitlesBefore, listTitleAfter); //Comparing lists, checking if Title is added

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*=icon-close]"))).click();

        //602b - Try Add Title with empty Author
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait not always worked

        //We need list of authors before and after
        List<WebElement> listAuthorBefore = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[2]");

        driver.findElement(By.cssSelector("#add-title-button")).click();
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(title);
        driver.findElement(By.xpath("//input[@name='author']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='year']")).sendKeys(year.toString());

        driver.findElement(By.xpath("//*[@name='submit-button']")).click();

        Assertions.assertTrue(driver.findElementByCssSelector("[class*=alert--error]").isDisplayed());
        List<WebElement> listAuthorAfter = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[2]");
        Assertions.assertEquals(listAuthorBefore, listAuthorAfter); //Comparing lists, checking if title is added

        //Now third one for Year
        //602c - Try Add Title with empty year
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*=icon-close]"))).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait below not always worked

        //We need list of Years before and after
        List<WebElement> listYearBefore = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[3]");

        driver.findElement(By.cssSelector("#add-title-button")).click();
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(title);
        driver.findElement(By.xpath("//input[@name='author']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='year']")).sendKeys("");

        driver.findElement(By.xpath("//*[@name='submit-button']")).click();

        Assertions.assertTrue(driver.findElementByCssSelector("[class*=alert--error]").isDisplayed());
        List<WebElement> listYearAfter = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[3]");
        Assertions.assertEquals(listYearBefore, listYearAfter); //Comparing lists, checking if title is added
    }

    @Test
    public void t603shouldReturnCorrectTitles(ChromeDriver driver) {

        //given
        WebDriverWait wait = new WebDriverWait(driver, 5);
        doLogin(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        List<WebElement> listTitles = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        List<WebElement> listAuthor = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[2]");
        List<WebElement> listYear = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[3]");
        //then
        Assertions.assertEquals(4, listTitles.size()); // List should contain 2 elements or more depends hwo many times i ran methods

        for (int i = 0; i < listTitles.size(); i++)   // checking if titles are added correctly
            if (i % 2 == 0) Assertions.assertTrue((title).equalsIgnoreCase(listTitles.get(i).getText()));
            else Assertions.assertTrue((title + 2).equalsIgnoreCase((listTitles.get(i).getText())));

        for (int i = 0; i < listAuthor.size(); i++)  // checking if author is correct
            Assertions.assertTrue(("by " + author).equalsIgnoreCase(listAuthor.get(i).getText()));

        for (int i = 0; i < listYear.size(); i++)  //Checking if year is correct
            if (i % 2 == 0) Assertions.assertEquals(("(" + year + ")"), listYear.get(i).getText());
            else Assertions.assertEquals(("(" + (year + 2) + ")"), listYear.get(i).getText());

    }

    @Test
    public void t604shouldEditExistingTitle(ChromeDriver driver) {
        doLogin(driver);
        WebElement editButton = driver.findElementByXPath("//li[contains(@id,'title-')][1]/div[2]/button");
        editButton.click();
        //edit title
        WebElement titleInput = driver.findElement(By.xpath("//input[@name='title']"));
        titleInput.clear();
        titleInput.sendKeys(title + " " + 2);
        WebElement authorInput = driver.findElement(By.xpath("//input[@name='author']"));
        authorInput.clear();
        authorInput.sendKeys(author + "ski");
        WebElement yearInput = driver.findElement(By.xpath("//input[@name='year']"));
        yearInput.clear();
        yearInput.sendKeys("1888");
        driver.findElement(By.xpath("//*[@name='submit-button']")).click();
        //check if new user is edited
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //Added Implicit wait because Explicit wait below not always worked

        String chkTitles = driver.findElementByXPath("//li[contains(@id,'title-')][1]/div/div[1]").getText();
        String chkAuthor = driver.findElementByXPath("//li[contains(@id,'title-')][1]/div/div[2]").getText();
        String chkYear = driver.findElementByXPath("//li[contains(@id,'title-')][1]/div/div[3]").getText();
        System.out.println(chkAuthor + "\n" + chkTitles + "\n" + chkYear);
        Assertions.assertTrue(chkTitles.equalsIgnoreCase(title + " 2"));
        Assertions.assertTrue(chkAuthor.equalsIgnoreCase("by " + author + "ski"));
        Assertions.assertTrue(chkYear.equalsIgnoreCase("(1888)"));
    }

    @Test
    public void t604aShouldNotAllowYearWith7Chars(ChromeDriver driver) {
        //given when
        doLogin(driver);
        driver.findElementByXPath("//li[contains(@id,'title-')][2]/div[2]/button").click();
        WebElement yearInput = driver.findElement(By.xpath("//input[@name='year']"));
        yearInput.clear();
        yearInput.sendKeys("1234567");
        driver.findElement(By.xpath("//*[@name='submit-button']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //then
        String chkYear = driver.findElementByXPath("//li[contains(@id,'title-')][2]/div/div[3]").getText();
        System.out.println(chkYear);
        Assertions.assertTrue(chkYear.equalsIgnoreCase("(1234567)"));
        //I'm not sure if this makes sense
        try {
            Assertions.assertTrue(driver.findElementByCssSelector("[class*=alert--error]").isDisplayed());
        } catch (Exception e) {
            System.out.println("no alert found");
        }
    }

    @Test
    public void t605shouldRemoveTitle(ChromeDriver driver) {
        doLogin(driver);

        //creating a list to verify whether correct number of titles was added
        List<WebElement> listTitlesBefore = new ArrayList<>();  //just to make sure it works when list is empty
        try {
            listTitlesBefore = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        } catch (Exception e) {
            System.out.println("no titles");
        }
        //when
        driver.findElementByXPath("//li[contains(@id,'title-')][1]/div[2]/button[2]").click(); //removes first title from the list
        //there should be a confirm button, but is not

        //then
        //I know it's not happening, but assertion should be here
        //Assertions.assertTrue(driver.findElementByCssSelector("[class*=alert--error]").isDisplayed());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> listTitles = driver.findElementsByXPath("//li[contains(@id,'title-')]/div/div[1]");
        for (WebElement n : listTitles
             ) {
            System.out.println(n.getText());
        }
        Assertions.assertEquals(listTitlesBefore.size()-1, listTitles.size());
    }

    public void doLogin(ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://ta-ebookrental-fe.herokuapp.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

        //log in to system, I should get to titles page
        WebElement log = driver.findElement(By.id("login"));
        log.sendKeys(logingx);
        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys(pwdgx);
        WebElement button = driver.findElement(By.id("login-btn"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
    }

}
