package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

  private String broser;

    public ApplicationManager(String broser) {
    this.broser = broser;
       properties = new Properties();
  }

  public void init() throws IOException {
      String target = System.getProperty("target","local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

      if (Objects.equals(broser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(broser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(broser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
      wd.manage().window().maximize();
      wd.get(properties.getProperty("web.baseUrl"));

    }

  public void stop() {
    wd.quit();
  }

}
