package ru.stqa.pft.addressbook.applicationManager;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("email"), contactDate.getMail());
    type(By.name("email2"), contactDate.getMail2());
    type(By.name("email3"), contactDate.getMail3());
    type(By.name("mobile"), contactDate.getMobilePhone());
    type(By.name("work"), contactDate.getWorkPhone());
    type(By.name("home"), contactDate.getHomePhone());
    type(By.name("address"), contactDate.getAddress());
   // attach(By.name("photo"), contactDate.getPhoto());


    if (creation){
      if(contactDate.getGroups().size()>0){
        Assert.assertTrue(contactDate.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroups().iterator().next().getName());
      }
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
    wd.findElement(By.id(""+ id +"")).click();
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
    List<WebElement> elements1 = wd.findElements(By.name("entry"));
    for (WebElement element : elements1) {
      List<WebElement>cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allphones = cells.get(5).getText();
      String allMail = cells.get(4).getText();
      String address = cells.get(3).getText();

      ContactDate contactDateId = new ContactDate().withId(id).withLastname(lastName).withFirstname(firstName)
              .withAllPhones(allphones).withAllMail(allMail).withAddress(address);
      contactCashe.add(contactDateId);
    }
    return new Contacts(contactCashe);
  }


  public ContactDate infoFromEditForm(ContactDate contactDate) {
    initContactModificationByID(contactDate.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactDate().withId(contactDate.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withtWorkPhone(work).withAddress(address);

  }

  private void initContactModificationByID(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public ContactDate infoFromEditFormByMail(ContactDate contactDate) {
    initContactModificationByID(contactDate.getId());
    String mail = wd.findElement(By.name("email")).getAttribute("value");
    String mail2 = wd.findElement(By.name("email2")).getAttribute("value");
    String mail3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactDate().withId(contactDate.getId()).withMail(mail).withMail2(mail2)
            .withMail3(mail3);

  }

  //method for PageTest

  private void initContactViewByID(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']",id))).click();
  }
  public ContactDate infoFrom(ContactDate contactDate) {
    initContactViewByID(contactDate.getId());
    String content = wd.findElement(By.xpath(".//*[@id='content']")).getText();
    wd.navigate().back();
    return new ContactDate().withContent(content);
  }

           public void toGroupsWithContact(ContactDate contactDate){
       new Select(wd.findElement(By.name("group"))).selectByVisibleText(contactDate.getGroups().iterator().next().getName());
  }
            public void selectGroupForContact(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
     }
           public void addContactToGroup(){
         click(By.name("add"));
           }

           public void deleteFromGroup() {
        click(By.name("remove"));
      }
  }









