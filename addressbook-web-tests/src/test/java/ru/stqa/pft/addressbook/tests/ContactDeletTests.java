package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;

public class ContactDeletTests extends TestBase {

  @Test
  public void testDeletTests() throws InterruptedException {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().isAlertPresent();
  }

}
