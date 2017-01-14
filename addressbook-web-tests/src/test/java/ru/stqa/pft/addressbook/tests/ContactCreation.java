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
import java.util.Set;

public class ContactCreation extends TestBase {

  @Test
  public void createNewContact(){
    app.goTo().gotoHome();
    Set<ContactDate> before = app.contact().allContact();
    app.goTo().contactPage();
    ContactDate contact = new ContactDate()
            .withLastname("test1")
          //  .withNickname("test4")
           // .withCompany("test4")
           // .withHome("test5")
            .withGroup("Test 1");
    app.contact().createContact(contact);
    Set<ContactDate> after = app.contact().allContact();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);

    Assert.assertEquals( after, before);
  }

}
