package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreation extends TestBase {

  @Test
  public void createNewContact(){
    app.getNavigationHelper().gotoHome();
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactDate contact = new ContactDate(null, null,null,"test4","test5","test6","Test 1" );
    app.getContactHelper().createContact(contact);
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    int max = 0;
    for (ContactDate g: after){
      if(g.getId()> max){
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

}
