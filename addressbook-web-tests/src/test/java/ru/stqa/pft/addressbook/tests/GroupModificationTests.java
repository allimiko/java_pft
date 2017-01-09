package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupModificationTests extends TestBase{

  @Test
  public void testCroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test 1", "test 1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().initGroupModification();
    GroupData groupData = new GroupData(before.get(before.size()-1).getId(),"Test 2", "test 2", "Test 1", "Test 1");
    app.getGroupHelper().fillGroupForm(groupData);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(before.size()-1);
    before.add(groupData);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

}
