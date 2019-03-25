package krasnikova.mantis.tests;

import krasnikova.mantis.model.MailMessage;
import krasnikova.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
   UserData changedUser = app.db().users().iterator().next();
   String newPassword = "root";
   app.login().loginAsAdmin();
   app.go().manageUsersPage();
   app.user().ressetPassword(changedUser.getId());
   List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
   String confirmationLink = findConfirmationLink(mailMessages, changedUser.getEmail());
   app.registration().finish(confirmationLink, newPassword);
   assertTrue(app.newSession().login(changedUser.getUsername(), newPassword));
}

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
