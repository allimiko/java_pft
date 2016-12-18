package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactDeletTests extends TestBase {

  @Test
  public void testDeletTests() throws InterruptedException {
    app.getContactHelper().selectContact();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactDate("test1","test2","test3","test4","test5","test6","test7"), false);
      app.getNavigationHelper().gotoHome();
    }
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().isAlertPresent();
  }


  }
