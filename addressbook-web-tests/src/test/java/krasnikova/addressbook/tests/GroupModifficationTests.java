package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class GroupModifficationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("new1"));
    }
  }

  @Test
  public void testGroupModification(){

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).
            withName("new1 edited").
            withHeader("Group header edited").
            withFooter("Group footer edited");
    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
