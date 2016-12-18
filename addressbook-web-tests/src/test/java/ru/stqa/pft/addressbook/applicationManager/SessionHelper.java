package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SessionHelper extends HelperBase {


  public SessionHelper( WebDriver wd) {

    super(wd);
  }
  public void login(String username, String password) {
    type(By.name("pass"),password);
    type(By.name("user"),username);
   click((By.xpath("//form[@id='LoginForm']/input[3]")));
  }
}