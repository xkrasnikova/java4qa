package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("TEST", "USER", null, null, null, "new1"),true);
    app.getContactHelper().submitContactCreation();
  }

}
