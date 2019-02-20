package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillNewContactForm(new ContactData("TEST", "USER", null, null, null));
    app.getContactHelper().submitContactCreation();
  }

}
