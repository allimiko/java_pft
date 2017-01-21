package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        app.goTo().GroupPage();
        if ( app.group().all().size() ==0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))){
                String line = reader.readLine();
                    String[] split = line.split(";");
                   GroupData groupData = new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]);
                app.group().create(groupData);
            }
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(),equalTo(before.size() - 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withOut(deletedGroup)));


    }



}

