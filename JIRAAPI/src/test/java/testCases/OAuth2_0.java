package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OAuth2_0 
{
	
	String ParsedaccessToken;
	
	WebDriver driver;
	
	String AUthCode;
	
  @Test(priority=1)
  public void authorizatinCode() throws Exception 
  {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resources\\Drivers\\chromedriver.exe");
	  
	  System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")+"\\target\\SeleniumLogs.txt");
	  
	  ChromeOptions options = new ChromeOptions();
	  
	 // Map<String, Object> prefs = new HashMap<String, Object>();
	  
	//  options.setExperimentalOption("prefs", prefs);
	  
	 // options.setExperimentalOption("useAutomationExtension", false);
	  
	 // options.addArguments("incognito");
		
	 // options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
	  
	  driver = new ChromeDriver(options);
	  
	  driver.manage().window().maximize();
	  
	 // driver.manage().deleteAllCookies();
	  
	  driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
	  
	  Actions action = new Actions(driver);
	  
	  action.sendKeys(Keys.ENTER).perform();
	  
	//  WebDriverWait wait = new WebDriverWait(driver,20);
	  
	 // WebElement emailField = driver.findElement(By.id("identifierId"));
	  
	  Thread.sleep(1000);
	  
	 // wait.until(ExpectedConditions.elementToBeClickable(emailField));
	  
	 // emailField.click();
	  
	  driver.findElement(By.id("identifierId")).sendKeys("oauthselenium");
	  
	  action.sendKeys(Keys.ENTER).perform();
	  
	  Thread.sleep(1000);
	  
	  WebElement Password = driver.findElement(By.xpath("//*[@name='password']"));
	  
	 // wait.until(ExpectedConditions.elementToBeClickable(Password));
	  
	 // Password.click();

	  Password.sendKeys("Dragonballz99");
	  
	  action.sendKeys(Keys.ENTER).perform();
	  
	  Thread.sleep(2000);
	  
	  String url = driver.getCurrentUrl();
	  
	  System.out.println(url);
	  
	  String  part = url.split("code=")[1];
	  
	  AUthCode = part.split("&scope")[0];
	  
	  System.out.println(AUthCode);
	  
	  driver.quit();
	  
	  
	 // AUthCode = firstSplit.split("&scope")[0];
	  
  }
  
  @Test(dependsOnMethods= {"authorizatinCode"})
  public void accessToken()
  {
	  String AccessToken = given().urlEncodingEnabled(false)
	  .queryParam("code", AUthCode)
	  .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	  .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	  .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
	  .queryParam("grant_type", "authorization_code")
	  .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
	  
	  JsonPath js = new JsonPath(AccessToken);
	  
	  ParsedaccessToken = js.getString("access_token");
	  
	  System.out.println("Access token is:"+" "+ParsedaccessToken);
	 
  }
  
  @Test(dependsOnMethods= {"accessToken","authorizatinCode"})
  public void apiCall()
  {
	  String response = given().queryParam("access_token", ParsedaccessToken)
	  .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
	  
	  System.out.println(response);
	
  }
}
