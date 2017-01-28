package ru.stqa.pft.mantis.mantis;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ChangePassword extends TestBase{
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void testChangePassword() throws IOException, InterruptedException, MessagingException {
        Users users = app.db().users();
        UserData modifiedUser = users.iterator().next();
        String userName = modifiedUser.getUserName();
        String email = modifiedUser.getEmail();
        String newPassword="345334534";

       app.getAdministratorHelper().loginAsAdministrator();
        app.getAdministratorHelper().goToManageUsers();
        app.getAdministratorHelper().resetPassword(userName);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 15000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink,newPassword);

        HttpSession session=app.newSession();
        assertTrue(session.login(userName,newPassword));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex=VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public  void stopMailServer(){
        app.mail().stop();
    }
}
