package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.applicationManager.HelperBase;
import ru.stqa.pft.addressbook.model.GroupDate;

/**
 * Created by Monsters on 04.12.2016.
 */
public class GroupModificationTests extends TestBase{

  @Test
  public void testCroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupDate("Test 1", "test 1", "Test 1", "Test 1"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }

}
