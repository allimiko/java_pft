package ru.stqa.pft.addressbook.tests;


import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.List;

public class ContactDeletTests extends TestBase {

  @Test
  public void testDeletTests() throws InterruptedException {
    app.goTo().gotoHome();
    if (! app.contact().isThereContact()){
      app.goTo().gotoHome();
      app.contact().createContact(new ContactDate()
              .withLastname("test3")
              .withNickname("test4")
              .withCompany("test4")
              .withHome("test5")
              .withGroup("test 1"));

    }
    List<ContactDate> before = app.contact().getContactList();
    int index = before.size() -1;
    app.contact().selectContact(index);
    app.contact().deleteSelectedContact();
    app.contact().isAlertPresent();
    List<ContactDate> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(after, before);

  }

}
