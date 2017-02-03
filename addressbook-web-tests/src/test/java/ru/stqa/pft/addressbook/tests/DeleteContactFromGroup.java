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
public class DeleteContactFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditionsContact() throws IOException {
        Groups groups = app.db().groups();
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
                String line = reader.readLine();
                String[] split = line.split(";");
                GroupData groupData = new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]);
                app.group().create(groupData);
            }
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoHome();
            app.contact().isThereContact();
            app.goTo().contactPage();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
                String line = reader.readLine();
                String[] split = line.split(";");
                ContactDate newContact = new ContactDate().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                        .withMail(split[3]).withMobilePhone(split[4]).inGroup(groups.iterator().next());
                app.contact().createContact(newContact);
            }
        }
        if(app.db().groups().iterator().next().getContacts().size()==0){
            Groups group = app.db().groups();
            Contacts before = app.db().contacts();
            int contactId = before.iterator().next().getId();
            int groupId = group.iterator().next().getId();
            app.contact().selectContactById(contactId);
            app.contact().selectGroupForContact(groupId);
            app.contact().addContactToGroup();
            }
        }


    @Test
    public void testDeleteContactFromGroup(){
        Groups groups = app.db().groups();
        ContactDate before = app.db().contacts().iterator().next().inGroup(groups.iterator().next());
        String nameOfGroup = before.getGroups().iterator().next().getName();
        int idContact = before.getId();
        app.goTo().gotoHome();
        app.contact().toGroupsWithContact(before);
        app.contact().selectContactById(idContact);
        app.contact().deleteFromGroup();

           int contactInGroup = app.db().groups().iterator().next().withName(nameOfGroup).getContacts().size();
        Assert.assertEquals(contactInGroup,0);

    }
}
