package krasnikova.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected WebDriver wd;

  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;

  public void init() {

    if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
