package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("new1", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

  int max = 0;
  for (GroupData g: after){
    if (g.getId() > max){
      max = g.getId();
    }
  }
  group.setId(max);
  before.add(group);
  Assert.assertEquals(new HashSet<Object>(before).toString(), new HashSet<Object>(after).toString());
  }
}
