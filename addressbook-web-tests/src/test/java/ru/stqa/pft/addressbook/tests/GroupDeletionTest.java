package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    
    @Test
    public void testGroupDeletion() {

        applicationManager.getNavigationHelper().gotoGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteSelectedGroups();
        applicationManager.getGroupHelper().returnToGroupPage();
    }


}
