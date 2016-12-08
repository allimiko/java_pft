package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HelperBase {
  protected  WebDriver wd;

  public HelperBase( WebDriver wd) {
    this.wd = wd;
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
    click(locator);
    if (text != null){
      wd.findElement(locator).clear();
      wd.findElement(locator).sendKeys(text);

    }
  }
  public  boolean isAlertPresent() {
    try {
      wd.switchTo().alert().accept();
      wd.switchTo().alert();
    } catch (NoAlertPresentException ex) {
      ex.printStackTrace();
    }
    return true;
  }

}
