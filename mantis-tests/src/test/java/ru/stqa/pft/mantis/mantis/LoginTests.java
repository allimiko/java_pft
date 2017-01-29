package ru.stqa.pft.mantis.mantis;


import static org.testng.AssertJUnit.assertTrue;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

/**
 * Created by Администратор on 24.01.2017.
 */
public class LoginTests extends TestBase{
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword")));
        assertTrue(session.isLoggedInAs(app.getProperty("web.adminLogin")));

    }
}
