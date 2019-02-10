package krasnikova.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGoupForm(new GroupData("new1", "Group header 1", "Group footer 2"));
    submitGroupCreation();
    returnToGroupPage();
  }


}
