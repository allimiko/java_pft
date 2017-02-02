package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Monsters on 09.12.2016.
 */
public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContact() throws IOException {
    if (app.db().contacts().size() == 0){
      app.goTo().gotoHome();
      app.contact().isThereContact();
      app.goTo().contactPage();
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
        String line = reader.readLine();
        String[] split = line.split(";");
        ContactDate newContact = new ContactDate().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                .withMail(split[3]).withMobilePhone(split[4]);
        app.contact().createContact(newContact);
    }
    }
  }

  @Test
  public void testContactModification() throws IOException {
   Contacts before = app.db().contacts();
   // Contacts before = app.contact().allContact();
    ContactDate modifiedContact = before.iterator().next();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contactsModif.csv")))) {
      String line = reader.readLine();
      String[] split = line.split(";");
      ContactDate contactDate = new ContactDate().withId(modifiedContact.getId()).withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
              .withMail(split[3]).withMobilePhone(split[4]);
        /*
    ContactDate contactDate = new ContactDate()
            .withId(modifiedContact.getId())
            .withFirstname("test45")
            .withMiddlename("test45")
            .withLastname("test45")
            .withNickname("test55")
            .withHomePhone("test55")
            .withCompany("test45");
            */
      app.contact().initContactModification(modifiedContact);
      app.contact().modification(contactDate);
      assertThat(app.contact().getContactCount(), equalTo(before.size()));
      Contacts after = app.db().contacts();
      //Contacts after = app.contact().allContact();

      assertThat(after, equalTo(before.withAdded(contactDate).withOut(modifiedContact)));
     verifyContactListInUI();
    }
  }


}