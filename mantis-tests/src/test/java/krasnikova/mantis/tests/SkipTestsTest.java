package krasnikova.mantis.tests;

import krasnikova.mantis.model.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SkipTestsTest extends TestBase{
  @Test
  public void testSkipped() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000002);
    }


  @Test
  public void testNotSkipped() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000001);
  }
}

