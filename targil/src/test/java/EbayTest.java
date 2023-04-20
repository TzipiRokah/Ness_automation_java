import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class EbayTest {

  EbayPOM ePom;
  WebDriver driver;
  WebDriverWait wait;

  @BeforeClass
  void setUp()
  {
    WebDriverManager.firefoxdriver().setup();
    driver=new FirefoxDriver();
    wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    ePom=new EbayPOM(driver,wait);
   ePom.goToPage();
  }

  @Description("Search item")
  @Test(priority = 1)
  void searchItem()
  {
    Assert.assertTrue(ePom.searchItem());
  }

  @Description("chose cooler")
  @Test(priority = 2)
  void chooseFilter()
  {
    Assert.assertTrue(ePom.chooseFilter());
  }

  @Description("add to cart")
  @Test(priority = 3)
  void addToCart()
  {
   Assert.assertTrue(ePom.addToCart());
  }
}
