package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Администратор on 28.01.2017.
 */
public class AdministratorHelper extends HelperBase{

    public AdministratorHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUsers(){
        click(By.linkText("Manage Users"));
    }

    public void loginAsAdministrator(){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value ='Login']"));
    }

    public void resetPassword(String userName) throws InterruptedException {
        click(By.linkText(userName));
       click(By.cssSelector("input[value ='Reset Password']"));
    }
}
