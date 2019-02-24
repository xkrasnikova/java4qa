package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModifficationTests extends TestBase {
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("new1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"new1 edited", "Group header 1 edited", "Group footer 2 edited");
    app.getGroupHelper().fillGoupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
