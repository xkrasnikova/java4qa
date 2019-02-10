package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGoupForm(new GroupData("new1", "Group header 1", "Group footer 2"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }


}
