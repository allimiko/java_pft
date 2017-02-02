package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {
  @DataProvider(name = "csv")
  public Iterator<Object[]> validContact() throws IOException {
    List<Object[]> list = new ArrayList<>();
    File photo = new File("src/test/resources/Shnake.png");
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))){
      String line = reader.readLine();
      while (line!=null){
        String[] split = line.split(";");
        list.add(new Object[]{new ContactDate().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                .withMail(split[3]).withMobilePhone(split[4]).withPhoto(photo).getGroups()});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line!=null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactDate> contactDates =  gson.fromJson(json, new TypeToken<List<ContactDate>>(){}.getType());
      return contactDates.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

  }

  @Test(dataProvider = "csv")
  public void createNewContact(ContactDate contact){
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    app.contact().createContact(contact);
    assertThat(app.contact().getContactCount(),equalTo(before.size() +1) );
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }


}
