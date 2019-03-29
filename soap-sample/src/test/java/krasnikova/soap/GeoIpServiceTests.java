package krasnikova.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

  @Test
  public void testMyUIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.122.60.140");
    assertTrue(geoIP.contains("<Country>UA</Country>"));
  }

  @Test
  public void testInvalidUIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.122.60.1dd");
    assertTrue(geoIP.contains("<Country>UA</Country>"));
  }
}

