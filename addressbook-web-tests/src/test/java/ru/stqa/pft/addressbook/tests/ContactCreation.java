package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

    Comparator<? super ContactDate> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.add(contact);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals( before, after);
  }

}
