package krasnikova.addressbook.tests;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.GroupData;
import krasnikova.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new1"));
    }
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("TEST").withLastName("USER").inGroup(groups.iterator().next()).withAddress("laalalalla\nlalalala"),true);
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    verifyContactListInUI();
  }



}
