package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("TEST", "USER", null, null, null, "new1"),true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
