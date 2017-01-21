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

public class ContactDeletTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    File photo = new File("src/test/resources/Shnake.png");
    app.goTo().gotoHome();
    if (!app.contact().isThereContact()) {
      app.goTo().contactPage();
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
        String line = reader.readLine();
          String[] split = line.split(";");
          ContactDate newContact = new ContactDate().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                  .withMail(split[3]).withMobilePhone(split[4]).withPhoto(photo);
        app.contact().createContact(newContact);
      }
    }
  }
    @Test
    public void testDeletTests() {
    Contacts before = app.contact().allContact();
    ContactDate contact = before.iterator().next();
    app.contact().deleteContact(contact);
      assertThat(app.contact().getContactCount(),equalTo(before.size()-1) );
    Contacts after = app.contact().allContact();
    assertThat(after, equalTo(before.withOut(contact)));
  }


  }


