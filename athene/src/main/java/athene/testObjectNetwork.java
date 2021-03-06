package athene;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class testObjectNetwork {
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
		
        WebElement s_menu_node = driver.findElement(By.xpath("//*[contains(text(), 'Network')]"));
		s_menu_node.click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAddincludedNetwork() throws InterruptedException {
		try {
			// Scenario
			// Add, Publish Button
		    WebElement btn_add = driver.findElement(By.xpath("//i[@class='fa fa-plus fa-fw']"));
		    WebElement btn_pub = driver.findElement(By.xpath("//i[@class='fa fa-floppy-o fa-fw']"));

		    btn_add.click();
		    Thread.sleep(1000);
			    
		    List<WebElement> networks = driver.findElements(By.xpath("//label[@class='ng-scope ng-isolate-scope']"));
		    String beforeNetwork = networks.get(0).getAttribute("innerHTML");
		    System.out.println(beforeNetwork);
		    
		    List<WebElement> name = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement nameValue = name.get(1);
		    nameValue.sendKeys("autotest1");
		    
		    List<WebElement> ipv4 = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement ipv4Value = ipv4.get(2);
		    ipv4Value.sendKeys("0.0.0.0");
		    
		    List<WebElement> mask = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement maskValueValue = mask.get(3);
		    maskValueValue.sendKeys("0.0.0.0");
		    
		    Select make = new Select(driver.findElement(By.xpath("//td[@id='0-4']/div/form/select")));
		    //assertEquals(2,make.getOptions().size());
		    make.selectByVisibleText("included");

		    List<WebElement> description = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement descriptionVaule = description.get(4);
		    descriptionVaule.sendKeys("for Auto Test");
		    
		    Thread.sleep(1000);
		    btn_pub.click();
		    
		    // Page Refresh
		    driver.navigate().refresh();
		    
			
			WebElement createdNetwork = driver.findElement(By.xpath("(//label[@class='ng-scope ng-isolate-scope'])[last()-4]"));
			String afterSid = createdNetwork.getAttribute("innerHTML");
			System.out.println(afterSid);
			
			assertEquals(beforeNetwork,afterSid);
			

			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}
	@Test
	public void testAddexcludedNetwork() throws InterruptedException {
		try {
			// Scenario
			// Add, Publish Button
		    WebElement btn_add = driver.findElement(By.xpath("//i[@class='fa fa-plus fa-fw']"));
		    WebElement btn_pub = driver.findElement(By.xpath("//i[@class='fa fa-floppy-o fa-fw']"));

		    btn_add.click();
		    Thread.sleep(1000);
			    
		    List<WebElement> networks = driver.findElements(By.xpath("//label[@class='ng-scope ng-isolate-scope']"));
		    String beforeNetwork = networks.get(0).getAttribute("innerHTML");
		    System.out.println(beforeNetwork);
		    
		    List<WebElement> name = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement nameValue = name.get(1);
		    nameValue.sendKeys("autotest2");
		    
		    List<WebElement> ipv4 = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement ipv4Value = ipv4.get(2);
		    ipv4Value.sendKeys("0.0.0.0");
		    
		    List<WebElement> mask = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement maskValueValue = mask.get(3);
		    maskValueValue.sendKeys("0.0.0.0");
		    
		    Select make = new Select(driver.findElement(By.xpath("//td[@id='0-4']/div/form/select")));
		    //assertEquals(2,make.getOptions().size());
		    make.selectByVisibleText("excluded");

		    List<WebElement> description = driver.findElements(By.xpath("//input[@type='text']"));
		    WebElement descriptionVaule = description.get(4);
		    descriptionVaule.sendKeys("for Auto Test");
		    
		    Thread.sleep(1000);
		    btn_pub.click();
		    
		    // Page Refresh
		    driver.navigate().refresh();
		    
			
			WebElement createdNetwork = driver.findElement(By.xpath("(//label[@class='ng-scope ng-isolate-scope'])[last()-4]"));
			String afterSid = createdNetwork.getAttribute("innerHTML");
			System.out.println(afterSid);
			
			assertEquals(beforeNetwork,afterSid);
			

			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}

/*	@Test
	public void testCheckBoxNetwork() throws InterruptedException {
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
	public void testDeleteNetwork() throws InterruptedException {
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
			
		    List<WebElement> networks = driver.findElements(By.xpath("//label[@class='ng-scope ng-isolate-scope']"));
			WebElement createdNetwork = driver.findElement(By.xpath("(//label[@class='ng-scope ng-isolate-scope'])[last()-4]"));
			String afterNetwork = createdNetwork.getAttribute("innerHTML");
			System.out.println(afterNetwork);
			
		    for (WebElement network : networks){
		    	System.out.println(network.getAttribute("innerHTML"));
				if (network.equals(afterNetwork)){
					fail();
				}
		    }
		    
			Thread.sleep(3000);

			driver.findElement(By.xpath("(//input[@type='checkbox'])[last()]")).click();
			Thread.sleep(1000);

			WebElement btn_del2 = driver.findElement(By.xpath("//i[@class='fa fa-minus fa-fw']"));
			WebElement btn_pub2 = driver.findElement(By.xpath("//i[@class='fa fa-minus fa-fw']"));


			btn_del2.click();
			Thread.sleep(1000);
			btn_pub2.click();
			
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
