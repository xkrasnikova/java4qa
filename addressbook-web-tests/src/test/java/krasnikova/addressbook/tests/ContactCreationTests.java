package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Pasha", "T", " Pashas address", "666666", "test22@test.com", "new1"), true);
    app.getContactHelper().submitContactCreation();
  }

}
