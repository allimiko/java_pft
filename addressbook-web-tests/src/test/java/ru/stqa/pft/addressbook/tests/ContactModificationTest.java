package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Monsters on 09.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.goTo().contactPage();
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
    ContactDate modifiedContact = before.iterator().next();
    app.contact().initContactModification(modifiedContact);
   // ContactDate contactDate = new ContactDate(before.get(index).getId(),before.get(index).getFirstname(),before.get(index).getMiddlename(),before.get(index).getLastname(),"test55","test55", null,null);
    ContactDate contactDate = new ContactDate().withId( modifiedContact.getId())
           // .withFirstname( modifiedContact.getFirstname())
           // .withMiddlename( modifiedContact.getMiddlename())
          //  .withLastname( modifiedContact.getLastname())
            .withFirstname("test45")
            .withMiddlename("test45")
            .withLastname("test45")
            .withNickname("test55")
            .withHome("test55");
    app.contact().modification(contactDate);
    Set<ContactDate> after = app.contact().allContact();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(contactDate);
    before.add(modifiedContact);
    Assert.assertEquals(before, after);
  }
}
