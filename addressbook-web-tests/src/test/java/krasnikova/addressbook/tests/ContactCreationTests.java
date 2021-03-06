package krasnikova.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.Contacts;
import krasnikova.addressbook.model.GroupData;
import krasnikova.addressbook.model.Groups;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new1"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromJSON")
  public void testContactCreation(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/1.jpeg");
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
}
