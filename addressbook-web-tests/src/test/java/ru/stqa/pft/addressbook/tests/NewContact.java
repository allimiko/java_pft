package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactDate;

public class NewContact extends TestBase {
 /*

  private void login(String password, String username) {
    applicationManager.get("http://localhost/addressbook/");
    applicationManager.findElement(By.name("pass")).click();
    applicationManager.findElement(By.name("pass")).clear();
    applicationManager.findElement(By.name("pass")).sendKeys(password);
    applicationManager.findElement(By.name("user")).click();
    applicationManager.findElement(By.name("user")).clear();
    applicationManager.findElement(By.name("user")).sendKeys(username);
    applicationManager.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  @Test
  public void newContactTest() {
    addNewContact();
    fillContactForm(new ContactDate("Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6"));
    createContact();

  }

  private void createContact() {
    applicationManager.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactDate contactDate) {
    applicationManager.findElement(By.name("firstname")).click();
    applicationManager.findElement(By.name("firstname")).clear();
    applicationManager.findElement(By.name("firstname")).sendKeys(contactDate.getFirstname());
    applicationManager.findElement(By.name("middlename")).click();
    applicationManager.findElement(By.name("middlename")).clear();
    applicationManager.findElement(By.name("middlename")).sendKeys(contactDate.getMiddlename());
    applicationManager.findElement(By.name("lastname")).click();
    applicationManager.findElement(By.name("lastname")).clear();
    applicationManager.findElement(By.name("lastname")).sendKeys(contactDate.getLastname());
    applicationManager.findElement(By.name("nickname")).click();
    applicationManager.findElement(By.name("nickname")).clear();
    applicationManager.findElement(By.name("nickname")).sendKeys(contactDate.getNickname());
    applicationManager.findElement(By.name("company")).click();
    applicationManager.findElement(By.name("company")).clear();
    applicationManager.findElement(By.name("company")).sendKeys(contactDate.getCompany());
    applicationManager.findElement(By.name("home")).click();
    applicationManager.findElement(By.name("home")).clear();
    applicationManager.findElement(By.name("home")).sendKeys(contactDate.getHome());
  }

  private void addNewContact() {
    applicationManager.findElement(By.linkText("add new")).click();
  }

  @AfterMethod
  public void tearDown() {
    applicationManager.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
  */
}
