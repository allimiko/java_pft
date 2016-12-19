package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

/**
 * Created by Monsters on 09.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactDate(null, null,"test3","test4","test5","test6","Test 1" ), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactDate("test55","test55","test55","test55","test55","test55", null), false);
    app.getContactHelper().submitContactModification();
  }
}
