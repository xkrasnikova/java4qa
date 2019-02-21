package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Edited", "Edited", " Edited address", "89876678", "edited@test.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
