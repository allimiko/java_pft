package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Monsters on 04.12.2016.
 */
public class NavigationHelper extends HelperBase {


  public NavigationHelper(ChromeDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoContactPage() {
    click(By.linkText("add new"));
  }

  public void gotoHome() {
    click(By.linkText("home"));
  }

}
