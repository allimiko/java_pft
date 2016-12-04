package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    applicationManager.getNavigationHelper().gotoGroupPage();
    applicationManager.getGroupHelper().initGroupCreation();
    applicationManager.getGroupHelper().fillGroupForm(new GroupDate("Test 1", "test 1", "Test 1", "Test 1"));
    applicationManager.getGroupHelper().submitGroupCreation();
    applicationManager.getGroupHelper().returnToGroupPage();
  }

}
