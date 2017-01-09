package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test 1", "test 1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() -1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);

    }


}

