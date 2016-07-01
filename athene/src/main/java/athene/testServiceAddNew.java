package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	    
	    WebElement username = driver.findElement(By.name("userid"));
		WebElement passwd = driver.findElement(By.name("pw"));
		WebElement login = driver.findElement(By.id("submit"));		

		// Login
		username.sendKeys("admin");
		passwd.sendKeys("openstack");
		
		login.submit();
		
        WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(m_menu_service));
		m_menu_service.click();
		
        WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
        s_menu_addnew.click();
	}
	
	@Test
	public void testNode() throws InterruptedException {
		try {
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
