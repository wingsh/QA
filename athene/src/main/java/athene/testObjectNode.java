package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class testObjectNode {
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
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement m_menu_object = driver.findElement(By.xpath("//*[contains(text(), 'Object')]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(m_menu_object));
		m_menu_object.click();
		
        WebElement s_menu_node = driver.findElement(By.xpath("//*[contains(text(), 'Node')]"));
		s_menu_node.click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAddNode() throws InterruptedException {
		try {
			// Scenario
			// Add, Publish Button
		    WebElement btn_add = driver.findElement(By.xpath("//i[@class='fa fa-plus fa-fw']"));
		    WebElement btn_pub = driver.findElement(By.xpath("//i[@class='fa fa-floppy-o fa-fw']"));

		    btn_add.click();
		    Thread.sleep(1000);
			    
		    List<WebElement> sids = driver.findElements(By.xpath("//label[@class='ng-scope ng-isolate-scope']"));
		    String beforeSid = sids.get(0).getAttribute("innerHTML");
		    System.out.println(beforeSid);
		    
		    List<WebElement> name = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement nameValue = name.get(1);
		    nameValue.sendKeys("autotest");
		    
		    List<WebElement> ipv4 = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement ipv4Value = ipv4.get(2);
		    ipv4Value.sendKeys("0.0.0.0");

		    List<WebElement> description = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement descriptionVaule = description.get(3);
		    descriptionVaule.sendKeys("for Auto Test");
		    
		    Thread.sleep(1000);
		    btn_pub.click();
		    
		    // Page Refresh
		    driver.navigate().refresh();
		    
			
			WebElement createdSid = driver.findElement(By.xpath("(//label[@class='ng-scope ng-isolate-scope'])[last()-3]"));
			String afterSid = createdSid.getAttribute("innerHTML");
			System.out.println(afterSid);
			
			assertEquals(beforeSid,afterSid);
			

			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}

/*	@Test
	public void testCheckBoxNode() throws InterruptedException {
		try {
			
		    WebElement checkboxAll = driver.findElement(By.cssSelector("input[type=checkbox]"));
		    List<WebElement> checkboxs = driver.findElements(By.xpath("//input[@class='ng-pristine ng-untouched ng-valid']"));

		    //해당 이슈는 Known Issue임 - AU-28
		    
		    if (!checkboxAll.isSelected()){
		    	System.out.println(checkboxAll.isSelected());
		    	checkboxAll.click();
		    }
		    
		    for(WebElement checkbox : checkboxs) {
		    	assertTrue(checkbox.isSelected());
		    }    
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}*/
	
	@Test
	public void testDeleteNode() throws InterruptedException {
		try {
			// 생성된 마지막 Row의 Node checkbox
			driver.findElement(By.xpath("(//input[@type='checkbox'])[last()]")).click();

			// Publish Button
			WebElement btn_del = driver.findElement(By.xpath("//i[@class='fa fa-minus fa-fw']"));
		    WebElement btn_pub = driver.findElement(By.xpath("//i[@class='fa fa-floppy-o fa-fw']"));

			btn_del.click();
			Thread.sleep(1000);
			btn_pub.click();

		    driver.navigate().refresh();
			
		    List<WebElement> sids = driver.findElements(By.xpath("//label[@class='ng-scope ng-isolate-scope']"));
			WebElement createdSid = driver.findElement(By.xpath("(//label[@class='ng-scope ng-isolate-scope'])[last()-3]"));
			String afterSid = createdSid.getAttribute("innerHTML");
			System.out.println(afterSid);
			
		    for (WebElement sid : sids){
		    	System.out.println(sid.getAttribute("innerHTML"));
				if (sid.equals(afterSid)){
					fail();
				}
		    }

			
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
