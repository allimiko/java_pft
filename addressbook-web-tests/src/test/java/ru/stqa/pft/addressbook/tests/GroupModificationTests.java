package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;


public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsGroup() throws IOException {
    if(app.db().groups().size() ==0){
      app.goTo().GroupPage();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
          String line = reader.readLine();
          String[] split = line.split(";");
          GroupData groupData = new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]);
          app.group().create(groupData);
      }
    }
  }

  @Test
  public void testCroupModification() throws IOException {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groupsModif.csv")))) {
      String line = reader.readLine();
      String[] split = line.split(";");
     GroupData groupData = new GroupData().withId(modifiedGroup.getId()).withName(split[0]).withHeader(split[1]).withFooter(split[2]);
    /*
    GroupData groupData = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("Test 2")
            .withHeader("test 2")
            .withHead("Test 1")
            .withFooter("Test 1");
            */
      app.goTo().GroupPage();
      app.group().modify(groupData);
      assertThat(app.group().count(), equalTo(before.size()));
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(groupData)));
        verifyContactListInUI();
    }

  }
}
