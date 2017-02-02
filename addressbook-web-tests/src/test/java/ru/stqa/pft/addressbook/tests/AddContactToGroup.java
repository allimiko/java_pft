package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by Администратор on 22.01.2017.
 */
public class AddContactToGroup extends TestBase {
    @Test
    public void testAddContactToGroup(){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactDate contactChoose = before.iterator().next();
        ContactDate contact = contactChoose.inGroup(groups.iterator().next());
        app.contact().selectContactById(contact.getId());


    }
}
