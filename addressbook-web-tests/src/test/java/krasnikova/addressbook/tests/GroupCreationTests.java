package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGoupForm(new GroupData("new1", "Group header 1", "Group footer 2"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
