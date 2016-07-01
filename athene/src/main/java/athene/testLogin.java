package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.*;
import static org.junit.Assert.*;

public class testLogin {
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
	public void testInvaildUser() throws InterruptedException {
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			username.sendKeys("invaild");
			Thread.sleep(1000);
			passwd.sendKeys("invaild");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			System.out.println(loginErrormsg);
			
			assertEquals(loginErrormsg, "invalid password or id");
			
			username.clear();
			passwd.clear();
		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	@Test
	public void testInvaildPasswd() throws InterruptedException {
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			username.sendKeys("admin");
			Thread.sleep(1000);
			passwd.sendKeys("invaild");
			Thread.sleep(1000);
						
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			System.out.println(loginErrormsg);
						
			assertEquals(loginErrormsg, "invalid password or id");
			
			username.clear();
			passwd.clear();

		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	@Test
	public void testVail() throws InterruptedException {
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			username.sendKeys("admin");
			Thread.sleep(1000);
			passwd.sendKeys("openstack");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);
			
			// Service
            WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
            m_menu_service.click();
			Thread.sleep(1000);
			
            //Check Service List Page
            WebElement s_menu_serviceList = driver.findElement(By.xpath("//*[contains(text(), 'Service List')]"));
            s_menu_serviceList.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene Chaining Services"))
				driver.close();
            
            //Check Add New Page
            WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
            s_menu_addnew.click();
			Thread.sleep(1000);     
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();
			
			// Object
            WebElement m_menu_object = driver.findElement(By.xpath("//*[contains(text(), 'Object')]"));
            m_menu_object.click();
			Thread.sleep(1000);
			
            //Check Node Page
            WebElement s_menu_node = driver.findElement(By.xpath("//*[contains(text(), 'Node')]"));
            s_menu_node.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene NODE Setting"))
				driver.close();
			
            //Check Network Page
            WebElement s_menu_network = driver.findElement(By.xpath("//*[contains(text(), 'Network')]"));
            s_menu_network.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene NETWORK Setting"))
				driver.close();
			
            //Check Range Page
            WebElement s_menu_range = driver.findElement(By.xpath("//*[contains(text(), 'Range')]"));
            s_menu_range.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene RANGE Setting"))
				driver.close();
			
	        //Check Group Page
            WebElement s_menu_group = driver.findElement(By.xpath("//*[contains(text(), 'Group')]"));
            s_menu_group.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene GROUP Setting"))
				driver.close();
			
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
