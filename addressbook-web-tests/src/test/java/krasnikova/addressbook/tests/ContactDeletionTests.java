package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("TEST", "USER", null, null, null, "new1"),true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeDeletionContactAlert();
    TimeUnit.SECONDS.sleep(4);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
