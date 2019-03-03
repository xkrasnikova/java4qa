package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("TEST").withLastName("USER").withGroup("new1"),true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    TimeUnit.SECONDS.sleep(4);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before,after);
  }

}
