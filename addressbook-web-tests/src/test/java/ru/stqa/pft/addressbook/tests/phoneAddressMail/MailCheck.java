package ru.stqa.pft.addressbook.tests.phoneAddressMail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Администратор on 15.01.2017.
 */
public class MailCheck extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
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
                    .withMail("erter@gmail.com")
                    .withMail2("ertejhjh")
                    .withMail3("ertr ")
                    .withGroup("Test 1"));
        }
    }

@Test
    public void testContactMail(){
    app.goTo().gotoHome();
    ContactDate contactDate = app.contact().allContact().iterator().next();
    ContactDate contactInfoFromMail = app.contact().infoFromEditFormByMail(contactDate);

    assertThat(contactDate.getAllMail(), equalTo(mergeMail(contactInfoFromMail)));
}


    private String mergeMail(ContactDate contact) {
        return asList(contact.getMail(), contact.getMail2(), contact.getMail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(MailCheck::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return mail.replaceAll("\\s","").replaceAll("[- ()]", "");

    }
}
