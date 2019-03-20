package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.Contacts;
import krasnikova.addressbook.model.GroupData;
import krasnikova.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest  extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() throws InterruptedException {
    Groups groups = app.db().groups();
    if (groups.size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new1"));
      app.goTo().homePage();
    }
    Contacts contacts = app.db().contacts();
    boolean isAddedToAnyGroup = false;
    for (ContactData contact : contacts) {
      // check if contact is added to any group
      if (!contact.getGroups().isEmpty()) {
        isAddedToAnyGroup = true;
        break;
      }
    }
    // Add new contact if there are contacts without groups
    if (!isAddedToAnyGroup){
      TimeUnit.SECONDS.sleep(1);
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("TEST").withLastName("USER").inGroup(groups.iterator().next()),true);
    }
  }

  @Test
  public void testContactRemovingFromGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      Groups before = contact.getGroups();
      if (before.size() == 0) {
        continue;
      }
      GroupData group = before.iterator().next();
      app.contact().displayContactListForGroup(group);
      app.contact().removeContactFromGroup(contact);
      app.goTo().homePage();
      ContactData updatedContact = app.db().contact(contact.getId());
      System.out.println("Contact " + contact.getId() + " was removed from group: " + group.getId());
      Groups after = updatedContact.getGroups();
      assertThat(after, equalTo(before.without(group)));
      verifyContactListInUI();
      break;
    }
  }
}
