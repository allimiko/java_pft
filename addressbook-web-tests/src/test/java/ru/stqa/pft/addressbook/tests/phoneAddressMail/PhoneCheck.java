package ru.stqa.pft.addressbook.tests.phoneAddressMail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.tests.TestBase;


import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class PhoneCheck extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHome();
        if (!app.contact().isThereContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactDate()
                    .withLastname("test3")
                    .withNickname("test4")
                    .withCompany("test4")
                    .withHomePhone("+7 (111)")
                    .withMobilePhone("345-345")
                    .withtWorkPhone("456 456")
                    .withGroup("Test 1"));
        }
    }
    @Test
    public void testContactPhones(){
        app.goTo().gotoHome();
        ContactDate contactDate = app.contact().allContact().iterator().next();
        ContactDate contactInfoFromeditForm = app.contact().infoFromEditForm(contactDate);

        assertThat(contactDate.getAllPhones(), equalTo(mergePhones(contactInfoFromeditForm)));
    }

    private String mergePhones(ContactDate contact) {
        return asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(PhoneCheck::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return  phone.replaceAll("\\s","").replaceAll("[- ()]", "");

}

}
