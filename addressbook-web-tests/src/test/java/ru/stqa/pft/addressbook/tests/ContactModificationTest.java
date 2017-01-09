package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Monsters on 09.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereContact()){
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactDate(null, null,"test3","test4","test5","test6","test 1" ));
    }
    List<ContactDate> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() -1);
    ContactDate contactDate = new ContactDate(before.get(before.size()-1).getId(),before.get(before.size()-1).getFirstname(),before.get(before.size()-1).getMiddlename(),before.get(before.size()-1).getLastname(),"test55","test55", null,null);
    app.getContactHelper().fillContactForm(contactDate, false);
    app.getContactHelper().submitContactModification();
    List<ContactDate> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(before.size()-1);
    before.add(contactDate);
    Comparator<? super ContactDate> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
