package ru.stqa.pft.addressbook.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

/**
 * Created by Monsters on 04.12.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactDate contactDate) {
    type(By.name("firstname"), contactDate.getFirstname());
    type(By.name("middlename"), contactDate.getMiddlename());
    type(By.name("lastname"), contactDate.getLastname());
    type(By.name("nickname"), contactDate.getNickname());
    type(By.name("company"), contactDate.getCompany());
    type(By.name("home"), contactDate.getHome());
  }

  public void submitContactCreation() {

    click(By.xpath(".//*[@id='content']/form/input[21]"));
  }
  public void selectContact() {

    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//input[@value='Delete']"));

  }
}







