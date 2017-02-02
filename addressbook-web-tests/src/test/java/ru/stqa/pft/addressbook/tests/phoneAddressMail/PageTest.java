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
public class PageTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHome();
        if (!app.contact().isThereContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactDate()
                    .withLastname("Melnik")
                    .withFirstname("Alex")
                    .withAddress("My World")
                    //.withCompany("test4")
                    .withMail("melnik@gmail.com")
                    .withMail2("melnik@mail.ru")
                    .withHomePhone("23 14 23")
                    .withMobilePhone("7 (068) 345 567 56"));
                    //.withGroup("Test 1"));
        }
    }

    @Test
    public void testContact(){
        app.goTo().gotoHome();
        ContactDate contactDate = app.contact().allContact().iterator().next();
        ContactDate contactInfoFromContactView = app.contact().infoFrom(contactDate);
        String stringFromHomePage = contactDate.getFirstname().concat(contactDate.getLastname()
                .concat(contactDate.getAddress().concat(contactDate.getAllPhones()
                        .concat(contactDate.getAllMail()))));
        assertThat(cleanedString(stringFromHomePage), equalTo(mergeView(contactInfoFromContactView)));

    }

    private String mergeView(ContactDate contact) {
        return asList(contact.getContent())
                .stream().filter((s) -> ! s.equals(""))
                .map(PageTest::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public  String cleanedString(String view) {
        return view.replaceAll("\\s", "").replaceAll("[- () ]", "");
    }

    public static String cleaned(String view) {
        return view.replaceAll("\\s", "").replaceAll("[- () ]", "")
                .replaceAll("H:","").replaceAll("M:","").replaceAll("W:","");

    }
}
