package krasnikova.addressbook.appmanager;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhone());
    type(By.name("email"),contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else Assert.assertFalse(isElementPresent(By.name("new_group")));

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void closeDeletionContactAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.name("entry")).get(index).findElement(By.cssSelector("img[title='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact, boolean isThereGroupField) {
    initContactCreation();
    fillContactForm(contact,isThereGroupField);
    submitContactCreation();
    returnToHomePage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List <WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String email = element.findElement(By.xpath(".//td[5]")).getText();
      String phone = element.findElement(By.xpath(".//td[6]")).getText();
      ContactData contact = new ContactData(id, firstName, lastName, address, phone, email, null);
      contacts.add(contact);
      }

    return contacts;
  }
}