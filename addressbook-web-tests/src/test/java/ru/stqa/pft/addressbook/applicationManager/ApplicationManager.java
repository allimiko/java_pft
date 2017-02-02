package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

  private SessionHelper sessionHelper;
  private  GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private String broser;
    private DbHelper dbhelper;

    public ApplicationManager(String broser) {
    this.broser = broser;
       properties = new Properties();
  }


  public void init() throws IOException {
      String target = System.getProperty("target","local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
      dbhelper = new DbHelper();


      if ("".equals(properties.getProperty("selenium.server"))) {
          if (Objects.equals(broser, BrowserType.CHROME)) {
              wd = new ChromeDriver();
          } else if (Objects.equals(broser, BrowserType.FIREFOX)) {
              wd = new FirefoxDriver();
          } else if (Objects.equals(broser, BrowserType.IE)) {
              wd = new InternetExplorerDriver();
          }
      } else {
          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setBrowserName(broser);
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")) ,capabilities);
      }

      wd.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
      wd.manage().window().maximize();
      wd.get(properties.getProperty("web.baseUrl"));
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
    }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db(){
        return dbhelper;
  }
}
