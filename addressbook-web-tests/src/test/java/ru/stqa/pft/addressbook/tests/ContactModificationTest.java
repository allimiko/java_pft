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
    app.contact().initContactModification(index);
   // ContactDate contactDate = new ContactDate(before.get(index).getId(),before.get(index).getFirstname(),before.get(index).getMiddlename(),before.get(index).getLastname(),"test55","test55", null,null);
    ContactDate contactDate = new ContactDate().withId(before.get(index).getId())
            .withFirstname(before.get(index).getFirstname())
            .withMiddlename(before.get(index).getMiddlename())
            .withLastname(before.get(index).getLastname())
            .withNickname("test55")
            .withHome("test55");
    app.contact().fillContactForm(contactDate, false);
    app.contact().submitContactModification();
    List<ContactDate> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(index);
    before.add(contactDate);
    Comparator<? super ContactDate> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
