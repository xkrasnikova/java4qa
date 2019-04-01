package krasnikova.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class SkipTestsTest extends TestBase{
  @Test
  public void testSkippedSoap() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixedSoap(0000002);
    }


  @Test
  public void testNotSkippedSoap() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixedSoap(0000001);
  }

}

