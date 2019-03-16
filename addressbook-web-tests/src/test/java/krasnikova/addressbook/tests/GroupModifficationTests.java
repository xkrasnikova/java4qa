package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.GroupData;
import krasnikova.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModifficationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new1"));
    }
  }

  @Test
  public void testGroupModification(){

    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).
            withName("new1 edited").
            withHeader("Group header edited").
            withFooter("Group footer edited");
    app.goTo().groupPage();
    app.group().modify(group);
    assertEquals(app.group().count(), before.size());
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}
