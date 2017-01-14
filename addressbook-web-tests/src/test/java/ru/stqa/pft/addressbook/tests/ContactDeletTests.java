package ru.stqa.pft.addressbook.tests;


import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletTests extends TestBase {

  @Test
  public void testDeletTests() throws InterruptedException {
    app.goTo().gotoHome();
    if (! app.contact().isThereContact()){
      app.goTo().contactPage();
      app.contact().createContact(new ContactDate()
              .withLastname("test3")
              .withNickname("test4")
              .withCompany("test4")
              .withHome("test5")
              .withGroup("Test 1"));

    }
    Set<ContactDate> before = app.contact().allContact();
    ContactDate contact = before.iterator().next();
    app.contact().deleteContact(contact);
    Set<ContactDate> after = app.contact().allContact();
    Assert.assertEquals(after.size(),before.size() -1);

    before.remove(contact);
    Assert.assertEquals(after, before);

  }


  }


