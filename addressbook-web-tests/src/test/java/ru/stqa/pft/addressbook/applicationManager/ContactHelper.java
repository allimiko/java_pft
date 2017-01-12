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
import java.util.List;

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

  public void deleteSelectedContact() {
    click(By.xpath(".//input[@value='Delete']"));

  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr/td[8]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void createContact(ContactDate contact) {
    fillContactForm(contact,true);
    submitContactCreation();
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
}







