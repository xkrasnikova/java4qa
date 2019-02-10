package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModifficationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGoupForm(new GroupData("new1 edited", "Group header 1 edited", "Group footer 2 edited"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
