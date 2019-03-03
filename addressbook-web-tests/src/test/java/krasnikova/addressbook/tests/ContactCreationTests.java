package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("new").withLastName("7").withGroup("new1");
    app.contact().create(contact,true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
