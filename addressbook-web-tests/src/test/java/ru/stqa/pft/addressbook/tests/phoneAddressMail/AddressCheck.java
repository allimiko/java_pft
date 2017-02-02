package ru.stqa.pft.addressbook.tests.phoneAddressMail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Администратор on 15.01.2017.
 */
public class AddressCheck extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        app.goTo().gotoHome();
        if (!app.contact().isThereContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactDate()
                    .withLastname("test3")
                    .withNickname("test4")
                    .withCompany("test4")
                    .withHomePhone("7 (111)")
                    .withMobilePhone("345-345")
                    .withtWorkPhone("456 456")
                    .withAddress("werwerewv 34343")
                    .inGroup(groups.iterator().next()));

        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().gotoHome();
        ContactDate contactDate = app.contact().allContact().iterator().next();
        ContactDate contactInfoFromeditForm = app.contact().infoFromEditForm(contactDate);

        assertThat(contactDate.getAddress(), equalTo(mergeAddress(contactInfoFromeditForm)));
    }

    private String mergeAddress(ContactDate contact) {
        return asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
