package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

import org.junit.*;
import static org.junit.Assert.*;

public class testAthene {
	protected WebDriver driver;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
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
	public void testAtheneAccess() throws InterruptedException {
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			Thread.sleep(1000);

			
			username.sendKeys("invaild");
			Thread.sleep(1000);
			passwd.sendKeys("invaild");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			//System.out.println(loginErrormsg);
			
			assertEquals(loginErrormsg, "invalid password or id");
			
			username.clear();
			passwd.clear();

			username.sendKeys("admin");
			Thread.sleep(1000);
			passwd.sendKeys("openstack");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);

			
            WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
            m_menu_service.click();
            
            WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
            s_menu_addnew.click();
            

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
         
    }

}
