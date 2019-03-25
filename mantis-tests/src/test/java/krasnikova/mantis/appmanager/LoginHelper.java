package krasnikova.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"),app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }
}
