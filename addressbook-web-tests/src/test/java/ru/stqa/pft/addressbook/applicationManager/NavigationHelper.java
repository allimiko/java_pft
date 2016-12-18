package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationHelper extends HelperBase {


  public NavigationHelper( WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoContactPage() {
    if (isElmentPresent(By.tagName("h1"))
            && findElement(By.tagName("h1")).getText().equals("Groups")
            && isElmentPresent(By.name("new"))){
      return;
    }
    click(By.linkText("add new"));
  }

  public void gotoHome() {
    if (isElmentPresent(By.name("MainForm"))){
      return;
    }
    click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));
  }

}
