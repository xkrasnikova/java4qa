package krasnikova.addressbook.genertors;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
        jCommander.parse(args);
      } catch (ParameterException ex) {
        jCommander.usage();
        return;
      }
      generator.run();
    }

    private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);
      saveAsJSON(contacts, new File(file));
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(contacts);
      try(Writer writer = new FileWriter(file)){
        writer.write(json);
      }
    }

    private List<ContactData> generateContacts(int count) {
      List<ContactData> contacts = new ArrayList<ContactData>();

      for (int i = 0; i< count; i++){
        contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
                .withLastName(String.format("LastName %s", i))
                .withMobilePhone(String.format("MobilePhone %s", i))
                .withHomePhone(String.format("HomePhone %s", i))
                .withWorkPhone(String.format("WorkPhone %s", i))
                .withEmail(String.format("email1+%s@mail.com", i))
                .withEmail2(String.format("email2+%s@mail.com", i))
                .withEmail3(String.format("email3+%s@mail.com", i))
                .withAddress(String.format("Address %s", i)));
      }
      return contacts;
    }
}
