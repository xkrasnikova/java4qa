package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.initContactCreation();
    app.fillNewContactForm(new ContactData("Pasha", "T", " Pashas address", "666666", "test22@test.com"));
    app.submitContactCreation();
  }

}
