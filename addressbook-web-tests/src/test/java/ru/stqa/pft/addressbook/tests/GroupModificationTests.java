package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;


public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsGroup(){
    app.goTo().GroupPage();
    if ( app.group().list().size() ==0) {
      app.group().create(new GroupData().withName("Test 1").withHeader("test1"));
    }

  }

  @Test
  public void testCroupModification(){
    List<GroupData> before = app.group().list();
    int index = before.size() -1;
    GroupData groupData = new GroupData()
            .withId(before.get(index).getId())
            .withName("Test 2")
            .withHeader("test 2")
            .withHead("Test 1")
            .withFooter("Test 1");
    app.group().modify(index, groupData);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(index);
    before.add(groupData);
    Comparator<? super GroupData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
