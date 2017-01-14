package ru.stqa.pft.addressbook.applicationManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactDate;

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
  }

  public void deleteContact(int index) {
    selectContact(index);
    deleteSelectedContact();
    isAlertPresent();
  }

  public void modification(ContactDate contactDate) {
    fillContactForm(contactDate, false);
    submitContactModification();
  }

  public void deleteContact(ContactDate contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    isAlertPresent();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }


    //public int getContactCount() {
      //return wd.findElements(By.name("selected[]")).size();
    //}

  public List<ContactDate > getContactList() {
    List<ContactDate> contactDates = new ArrayList<>();
    List<WebElement>elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr/td[2]"));
    List<WebElement>elements1 = wd.findElements(By.tagName("selected[]"));
    for (WebElement element : elements) {
      String lastName = element.getText();
      ContactDate contactDate = new ContactDate().withLastname(lastName);
      contactDates.add(contactDate);
    }
    for (WebElement element : elements1) {
       int id = Integer.parseInt(element.findElement(By.tagName("selected[]")).getAttribute("id"));
      ContactDate contactDate = new ContactDate().withId(id);
      contactDates.add(contactDate);

    }
    return contactDates;
  }

  public Set<ContactDate > allContact() {
    Set<ContactDate> contactDates = new HashSet<ContactDate>();
    List<WebElement> elements1 = wd.findElements(By.xpath(".//input[@name='selected[]']"));
    for (WebElement element : elements1) {
      int id = Integer.parseInt(element.getAttribute("value"));
      String lastName = wd.findElement(By.xpath(".//*[@id='maintable']/tbody/tr/td[2]")).getText();
      ContactDate contactDateId = new ContactDate().withId(id).withLastname(lastName);
      contactDates.add(contactDateId);
    }


    return contactDates;
  }


}








