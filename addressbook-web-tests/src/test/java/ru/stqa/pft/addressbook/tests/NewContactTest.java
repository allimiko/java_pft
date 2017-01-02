package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactDate;

public class NewContactTest extends TestBase {

  @Test
  public void createNewContact(){
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactDate(null, null,"test3","test4","test5","test6","Test 1" ));
  }

}
