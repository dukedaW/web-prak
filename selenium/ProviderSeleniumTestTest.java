// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class ProviderSeleniumTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void providerSeleniumTest() {
    driver.get("http://localhost:8080/");
    assertThat(driver.getTitle(), is("Главная страница"));
    driver.manage().window().setSize(new Dimension(1200, 954));
    driver.findElement(By.id("sellersLink")).click();
    assertThat(driver.getTitle(), is("Поставщики"));
    driver.findElement(By.id("addSellerButton")).click();
    driver.findElement(By.id("sellerId")).click();
    driver.findElement(By.id("sellerId")).sendKeys("3");
    driver.findElement(By.id("sellerName")).click();
    driver.findElement(By.id("sellerName")).sendKeys("kate");
    driver.findElement(By.id("sellerDescription")).click();
    driver.findElement(By.id("sellerDescription")).sendKeys("kate");
    driver.findElement(By.id("sellerPhone")).click();
    driver.findElement(By.id("sellerPhone")).sendKeys("kate");
    driver.findElement(By.id("sellerAddress")).click();
    driver.findElement(By.id("sellerAddress")).sendKeys("kate");
    driver.findElement(By.id("submitButton")).click();
    driver.findElement(By.id("sellersLink")).click();
    driver.findElement(By.cssSelector("tr:nth-child(4) a > span")).click();
    assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("kate"));
    assertThat(driver.findElement(By.cssSelector("p:nth-child(3)")).getText(), is("Адрес: kate"));
    driver.findElement(By.id("editButton")).click();
    driver.findElement(By.id("sellerAddress")).click();
    driver.findElement(By.id("sellerAddress")).sendKeys("kate, 1");
    driver.findElement(By.id("submitButton")).click();
    driver.findElement(By.id("sellersLink")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("kate");
    driver.findElement(By.cssSelector(".col > .btn")).click();
    driver.findElement(By.cssSelector("a > span")).click();
    assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("kate"));
    assertThat(driver.findElement(By.cssSelector("p:nth-child(3)")).getText(), is("Адрес: kate, 1"));
    driver.findElement(By.id("deleteButton")).click();
    driver.findElement(By.id("rootLink")).click();
  }
}
