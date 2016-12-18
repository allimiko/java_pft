package ru.stqa.pft.addressbook.applicationManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactDate;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void fillContactForm(ContactDate contactDate, boolean creation) {
    type(By.name("firstname"), contactDate.getFirstname());
    type(By.name("middlename"), contactDate.getMiddlename());
    type(By.name("lastname"), contactDate.getLastname());
    type(By.name("nickname"), contactDate.getNickname());
    type(By.name("company"), contactDate.getCompany());
    type(By.name("home"), contactDate.getHome());

    if (creation) {
      new Select(findElement(By.name("new"))).selectByVisibleText(contactDate.getGroup());
    } else {
      Assert.assertFalse(isElmentPresent(By.name("new")));
    }

  }

  public void submitContactCreation() {

    click(By.xpath("//*[@id='content']/*/input[@value='Enter']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact()  {
    click(By.xpath(".//*[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModification() {
    click(By.xpath(".//*[@id='maintable']/tbody/tr[2]/td[8]"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void createContact(ContactDate contact, boolean creation) {
    fillContactForm (contact, creation);
    submitContactCreation();
   }

  public boolean isThereAContact() {
    return isElmentPresent(By.xpath("selected[]"));
  }

}






