package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Monsters on 09.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    app.goTo().contactPage();
    if (!app.contact().isThereContact()) {
      app.goTo().contactPage();
      app.contact().createContact(new ContactDate()
              .withLastname("test3")
              .withNickname("test4")
              .withCompany("test4")
              .withHome("test5")
              .withGroup("Test 1"));
    }
    Contacts before = app.contact().allContact();
    ContactDate modifiedContact = before.iterator().next();
    app.contact().initContactModification(modifiedContact);
    ContactDate contactDate = new ContactDate().withId(modifiedContact.getId())
            .withFirstname("test45")
            .withMiddlename("test45")
            .withLastname("test45")
            .withNickname("test55")
            .withHome("test55");
    app.contact().modification(contactDate);
    Contacts after = app.contact().allContact();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(contactDate).withAdded(modifiedContact)));
  }
}