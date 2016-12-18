package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
  WebDriver wd;

  private SessionHelper sessionHelper;
  private  GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private String broser;

  public ApplicationManager(String broser) {
    this.broser = broser;
  }


  public void init() {
      if (Objects.equals(broser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(broser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(broser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      wd.manage().window().maximize();
      wd.get("http://localhost/addressbook/group.php");
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper.login("admin", "secret");
    }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
