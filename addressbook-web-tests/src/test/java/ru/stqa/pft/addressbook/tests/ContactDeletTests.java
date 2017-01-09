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
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactDate("test1", "test5","test3","test4","test5","test6","Test 1"));

    }
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().isAlertPresent();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(after, before);

  }

}
