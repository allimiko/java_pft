package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupDate("Test 1", "test 1", "Test 1", "Test 1"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
