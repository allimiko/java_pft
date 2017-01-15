package ru.stqa.pft.addressbook.tests;


import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ContactDeletTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoHome();
    if (! app.contact().isThereContact()) {
      app.goTo().contactPage();
      app.contact().createContact(new ContactDate()
              .withLastname("test3")
              .withNickname("test4")
              .withCompany("test4")
              .withHome("test5")
              .withGroup("Test 1"));
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


