package krasnikova.mantis.tests;

import krasnikova.mantis.appmanager.ApplicationManager;
import krasnikova.mantis.model.MailMessage;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
