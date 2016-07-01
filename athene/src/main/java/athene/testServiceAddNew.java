package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.*;
import static org.junit.Assert.*;

public class testServiceAddNew {
	protected WebDriver driver;	
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://10.61.11.50:8009/");
		
    	driver.manage().window().maximize();

	    String loginWindow = driver.getWindowHandle();
	    driver.switchTo().window(loginWindow);  
	}
	
	@Test
	public void testLogin() throws InterruptedException {
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			Thread.sleep(1000);
			

			// Login
			username.sendKeys("admin");
			Thread.sleep(1000);
			passwd.sendKeys("openstack");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);
			
			// Scenario
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}
    @After
    public void tearDown() throws Exception {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        driver.quit();
    }

}
