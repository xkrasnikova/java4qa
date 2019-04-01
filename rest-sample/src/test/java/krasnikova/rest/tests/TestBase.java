package krasnikova.rest.tests;

import krasnikova.rest.appmanager.ApplicationManager;
import krasnikova.rest.model.Issue;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;


import java.io.IOException;


public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));


  public boolean isIssueOpenRest(int issueId) throws IOException {
    Issue issue = app.rest().getIssue(issueId);
    return !issue.getState_name().equals("Closed");
  }

  public void skipIfNotFixedRest(int issueId) throws IOException {
    if (isIssueOpenRest(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
