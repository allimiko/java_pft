package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactDeletTests extends TestBase {

  @Test
  public void testDeletTests() throws InterruptedException {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactDate(null, null,"test3","test4","test5","test6","Test 1" ));

    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().isAlertPresent();
  }

}
