package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.*;

import java.io.File;


public class HelperBase {
  public    WebDriver wd;
  public WebElement element;

  public WebElement findElement(By locator){
  return   element = wd.findElement(locator);
  }

  public HelperBase( WebDriver wd) {
    this.wd = wd;
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
    if (text != null){
      element = findElement(locator);
      element.clear();
      element.sendKeys(text);

    }
  }
  public void attach (By locator, File file) {
    if (file != null){
      wd.findElement(locator).sendKeys(file.getAbsolutePath());

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

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }


}
