package athene;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class testServiceAddNew {
	protected WebDriver driver;	
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://10.61.11.71:8009/");
		
    	driver.manage().window().maximize();

	    String loginWindow = driver.getWindowHandle();
	    driver.switchTo().window(loginWindow);  
	    
	    WebElement username = driver.findElement(By.name("userid"));
		WebElement passwd = driver.findElement(By.name("pw"));
		WebElement login = driver.findElement(By.id("submit"));		

		// Login
		username.sendKeys("athene");
		passwd.sendKeys("athene");
		
		login.submit();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
        WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(m_menu_service));
		m_menu_service.click();
		
        WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
        s_menu_addnew.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}
	
	@Test
	public void testNS() throws InterruptedException {
		WebElement nsTitle =  driver.findElement(By.xpath("//input[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));
		WebElement nsDescription =  driver.findElement(By.xpath("//textarea[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));

		nsTitle.sendKeys("atheneAutoTest");
		nsDescription.sendKeys("atheneAutoTest");
		Thread.sleep(3000);
		
		WebElement nsPanel = driver.findElement(By.className("isul-svg-panel"));
		WebElement simpleFW = driver.findElement(By.id("nfdg-001:nfd-849182"));
		WebElement attoLB = driver.findElement(By.id("nfdg-004:nfd-215490"));
		WebElement filter = driver.findElement(By.id("nfdg-005:nfd-018391"));

		
		Actions operation = new Actions(driver);
		operation.dragAndDrop(simpleFW, nsPanel).perform();
		operation.dragAndDrop(attoLB, nsPanel).perform();
		operation.dragAndDrop(filter, nsPanel).perform();
		
	    List<WebElement> vnfList = driver.findElements(By.className("isul-svg-nf-title"));
	    
	    System.out.println("=== VNF id information ===");
	    String vportInID = vnfList.get(0).findElement(By.xpath("..")).getAttribute("id");
	    System.out.println("vport-in id is "+ vportInID);
	    String vportOutID = vnfList.get(1).findElement(By.xpath("..")).getAttribute("id");
	    System.out.println("vport-out id is "+ vportOutID);
	    String simpleFWID = vnfList.get(2).findElement(By.xpath("..")).getAttribute("id");
	    System.out.println("simple-FW id is "+ simpleFWID);
	    String attoLBID = vnfList.get(3).findElement(By.xpath("..")).getAttribute("id");
	    System.out.println("attoLB id is "+ attoLBID);
	    String filterID = vnfList.get(4).findElement(By.xpath("..")).getAttribute("id");
	    System.out.println("filter id is "+ filterID);
	    System.out.println("================");
	    
	    List<WebElement> vnf = driver.findElements(By.cssSelector("circle"));
	    WebElement vport_in_In = vnf.get(0);
	    WebElement vport_out_Out = vnf.get(1);
	    WebElement simpleFW_In = vnf.get(2);
	    WebElement simpleFW_Out = vnf.get(3);
	    WebElement attoLB_In = vnf.get(4);
	    WebElement attoLB_Out = vnf.get(5);
	    WebElement filter_In = vnf.get(6);
	    WebElement filter_Out = vnf.get(7);

	    System.out.println("vport_in_In id is" + vport_in_In.getAttribute("id"));
	    System.out.println("vport_out_Out id is" + vport_out_Out.getAttribute("id"));
	    System.out.println("simpleFW_In id is" + simpleFW_In.getAttribute("id"));
	    System.out.println("simpleFW_Out id is" + simpleFW_Out.getAttribute("id"));
	    System.out.println("attoLB_In id is" + attoLB_In.getAttribute("id"));
	    System.out.println("attoLB_Out id is" + attoLB_Out.getAttribute("id"));
	    System.out.println("filter_In id is" + filter_In.getAttribute("id"));
	    System.out.println("filter_Out id is" + filter_Out.getAttribute("id"));

	   
	   /* 
	    System.out.println(vnfList.get(0).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).getAttribute("id"));
	    System.out.println(vnfList.get(1).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).getAttribute("id"));
	    System.out.println(vnfList.get(2).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).getAttribute("id"));
	    System.out.println(vnfList.get(3).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).get(0).getAttribute("id"));
	    System.out.println(vnfList.get(3).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).getAttribute("id"));
	    System.out.println(vnfList.get(4).findElement(By.xpath("..")).findElement(By.cssSelector("circle")).getAttribute("id"));
		*/
		
	
		//port
		WebElement vportIn = driver.findElement(By.id("vport-ns-in"));
		WebElement vportOut = driver.findElement(By.id("vport-ns-out"));

		int vportInWidth = vportIn.getSize().getWidth();
        //System.out.println("size of port-in : " + vportInWidth);
        int vportInHeight = (vportIn.getSize().getHeight())/2;
        //System.out.println("size of port-in : " + vportInHeight);

        int vportOutHeight = (vportIn.getSize().getHeight())/2;
        //System.out.println("size of port-out : " + vportOutHeight);
        
        operation.moveToElement(vportIn,vportInWidth,vportInHeight).clickAndHold().moveToElement(vportOut,0,vportOutHeight).build().perform();;      

		Thread.sleep(3000);    
		
		try {
			//assertEquals("Dropped", nsPanel.getText());
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
