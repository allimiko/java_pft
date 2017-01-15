package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {

  @Test
  public void createNewContact(){
    app.goTo().gotoHome();
    Contacts before = app.contact().allContact();
    app.goTo().contactPage();
    ContactDate contact = new ContactDate()
            .withLastname("test1")
            .withNickname("test4")
            .withCompany("test4")
            .withHomePhone("test5")
            .withGroup("Test 1");
    app.contact().createContact(contact);
    assertThat(app.contact().getContactCount(),equalTo(before.size() +1) );
    Contacts after = app.contact().allContact();
    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
