package ru.stqa.pft.addressbook.applicationManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Monsters on 04.12.2016.
 */
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


    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {

    click(By.xpath("//*[@id='content']/*/input[@value='Enter']"));
  }
  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void selectContactById(int id) {
    wd.findElement(By.id(""+id+"")).click();
}

  public void deleteSelectedContact() {
    click(By.xpath(".//input[@value='Delete']"));

  }

  public void initContactModification(ContactDate date) {
    selectContactById(date.getId());
   wd.findElement(By.xpath(".//*[@id='maintable']/tbody/tr/td[8]")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void createContact(ContactDate contact) {
    fillContactForm(contact,true);
    submitContactCreation();
    contactCashe = null;
  }

  public void deleteContact(int index) {
    selectContact(index);
    deleteSelectedContact();
    isAlertPresent();
  }

  public void modification(ContactDate contactDate) {
    fillContactForm(contactDate, false);
    submitContactModification();
    contactCashe = null;
  }

  public void deleteContact(ContactDate contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCashe = null;
    isAlertPresent();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }


    public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    }

  private Contacts contactCashe = null;

  public Contacts allContact() {
    if (contactCashe != null){
      return new Contacts(contactCashe);

    }
    contactCashe = new Contacts();
    List<WebElement> elements1 = wd.findElements(By.xpath(".//input[@name='selected[]']"));
    for (WebElement element : elements1) {
      int id = Integer.parseInt(element.getAttribute("value"));
      String lastName = wd.findElement(By.xpath(".//*[@id='maintable']/tbody/tr/td[2]")).getText();
      ContactDate contactDateId = new ContactDate().withId(id).withLastname(lastName);
      contactCashe.add(contactDateId);
    }


    return new Contacts(contactCashe);
  }


}








