package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Администратор on 22.01.2017.
 */
public class AddContactToGroup extends TestBase {

    boolean result = false;

    @BeforeMethod
    public void ensurePreconditionsContact() throws IOException {
        if (app.db().contacts().size() == 0) {
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
        } else if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
                String line = reader.readLine();
                String[] split = line.split(";");
                GroupData groupData = new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]);
                app.group().create(groupData);
            }
        }
    }


    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        int contactId = before.iterator().next().getId();
        String contactName = groups.iterator().next().getName();
        ContactDate contactDate = before.iterator().next();
        int groupId = groups.iterator().next().getId();
        app.contact().selectContactById(contactId);
        app.contact().selectGroupForContact(groupId);
        app.contact().addContactToGroup();
       ContactDate date =  contactDate.inGroup(app.db()
               .groups().iterator().next().withId(groupId).withName(contactName));
             Assert.assertEquals(date, contactDate);


    }

}
