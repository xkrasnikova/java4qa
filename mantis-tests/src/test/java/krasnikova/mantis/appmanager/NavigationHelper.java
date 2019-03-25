package krasnikova.mantis.appmanager;

import krasnikova.mantis.tests.TestBase;
import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsersPage() {
    if (isElementPresent(By.linkText("Manage Accounts"))) {
      return;
    }
    click(By.linkText("Manage Users"));
  }
}
