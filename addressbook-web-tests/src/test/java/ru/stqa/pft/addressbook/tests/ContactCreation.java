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
    app.goTo().gotoHome();
    List<ContactDate> before = app.contact().getContactList();
    app.goTo().contactPage();
    ContactDate contact = new ContactDate()
            .withLastname("test3")
            .withNickname("test4")
            .withCompany("test4")
            .withHome("test5")
            .withGroup("Test 1");
    app.contact().createContact(contact);
    List<ContactDate> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactDate> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals( after, before);
  }

}
