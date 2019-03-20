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

public class ContactAddingToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws InterruptedException {
    Groups groups = app.db().groups();
    if (groups.size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new1"));
      app.goTo().homePage();
    }
    Contacts contacts = app.db().contacts();
    boolean isAddedToAllGroups = true;
    for (ContactData contact : contacts) {
      // check if contact is added to all groups
      if (!contact.getGroups().equals(groups)) {
        isAddedToAllGroups = false;
        break;
      }
    }
    // Add new contact if all contacts are added to all available groups
    if (isAddedToAllGroups){
      TimeUnit.SECONDS.sleep(1);
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("TEST").withLastName("USER"),true);
    }
  }

  @Test
  public void testContactAddingToGroup() throws Exception {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      Groups before = contact.getGroups();
      if (before.size() == groups.size()) {
        continue;
      }
      //remove groups from list where contact is already added
      groups.removeAll(before);

      GroupData group = groups.iterator().next();
      app.contact().addContactToGroup(contact, group);
      ContactData updatedContact = app.db().contact(contact.getId());
      System.out.println("Contact " + contact.getId() + " was added to group: " + group.getId());
      Groups after = updatedContact.getGroups();
      assertThat(after, equalTo(before.withAdded(group)));
      verifyContactListInUI();
      break;
    }
  }
}
