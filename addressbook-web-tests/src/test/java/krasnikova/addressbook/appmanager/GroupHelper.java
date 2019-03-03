package krasnikova.addressbook.appmanager;

import krasnikova.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<GroupData>();
    List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.getText();
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }


  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGoupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.xpath("(//input[@name='delete'])[2]"));
  }


  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initGroupModification() {
    click(By.xpath("(//input[@name='edit'])[2]"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGoupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void modify( GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGoupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }



  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
