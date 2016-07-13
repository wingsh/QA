package athene;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
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
		Thread.sleep(3000);
		/*
		WebElement nsTitle =  driver.findElement(By.xpath("//input[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));
		WebElement nsDescription =  driver.findElement(By.xpath("//textarea[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));

		nsTitle.sendKeys("atheneAutoTest");
		nsDescription.sendKeys("atheneAutoTest");
		Thread.sleep(3000);
	
		Actions operation = new Actions(driver);

		WebElement zoomBar =  driver.findElement(By.id("zoom-bar"));
        WebElement zoonOut = driver.findElement(By.xpath("//*[contains(text(), '-')]"));

		operation.dragAndDrop(zoomBar, zoonOut);
		
		WebElement nsPanel = driver.findElement(By.className("isul-svg-panel"));
		WebElement filter = driver.findElement(By.id("nfdg-005:nfd-018391"));
		WebElement simpleFW = driver.findElement(By.id("nfdg-001:nfd-849182"));
		WebElement attoLB = driver.findElement(By.id("nfdg-004:nfd-215490"));

		int nsPanel_Width = nsPanel.getSize().getWidth();
		System.out.println("nsPanel_Width : " + nsPanel_Width);
        int nsPanel_Height = nsPanel.getSize().getHeight();
		System.out.println("nsPanel_Height : " + nsPanel_Height);

		operation.dragAndDrop(filter, nsPanel).build().perform();
		Thread.sleep(2000);

		List<WebElement> test1 = driver.findElements(By.className("isul-svg-nf-title"));
	    WebElement test1_1 = test1.get(2);
	    operation.moveToElement(test1_1).clickAndHold().moveToElement(nsPanel, (nsPanel_Width/2)+50, (nsPanel_Height/2)+100).build().perform();
		Thread.sleep(2000);
		
		operation.dragAndDrop(simpleFW, nsPanel).build().perform();
		Thread.sleep(2000);	
		
	    List<WebElement> test2 = driver.findElements(By.className("isul-svg-nf-title"));
	    WebElement test2_1 = test2.get(2);
	    operation.moveToElement(test2_1).clickAndHold().moveToElement(nsPanel, (nsPanel_Width/2)-150, (nsPanel_Height/2)+50).build().perform();
		Thread.sleep(2000);
		
		operation.dragAndDrop(attoLB, nsPanel).build().perform();
		Thread.sleep(2000);
		
	    //List<WebElement> vnfList = driver.findElements(By.className("isul-svg-nf-title"));
	    
	    
	    System.out.println("=== VNF id information ===");
	    WebElement vportInID = vnfList.get(0);
	    System.out.println("vport-in id is "+ vportInID.findElement(By.xpath("..")).getAttribute("id"));
	    
	    WebElement vportOutID = vnfList.get(1);
	    System.out.println("vport-out id is "+ vportOutID.findElement(By.xpath("..")).getAttribute("id"));
	    
	    WebElement filterID = vnfList.get(2);
	    System.out.println("filter id is "+ filterID.findElement(By.xpath("..")).getAttribute("id"));
	    
	    WebElement simpleFWID = vnfList.get(3);
	    System.out.println("simple-FW id is "+ simpleFWID.findElement(By.xpath("..")).getAttribute("id"));
	    
	    WebElement attoLBID = vnfList.get(4);
	    System.out.println("attoLB id is "+ attoLBID.findElement(By.xpath("..")).getAttribute("id"));
	

	    System.out.println("================");
	    
	    
	    List<WebElement> vnf = driver.findElements(By.cssSelector("circle"));
	    WebElement vportIn_Out = vnf.get(0);
	    WebElement vportOut_In = vnf.get(1);
	    WebElement filter_In = vnf.get(2);
	    WebElement filter_Out = vnf.get(3);
	    WebElement simpleFW_In = vnf.get(4);
	    WebElement simpleFW_Out = vnf.get(5);
	    WebElement attoLB_In = vnf.get(6);
	    WebElement attoLB_Out = vnf.get(7);

	    System.out.println("vport in Out port id is " + vportIn_Out.getAttribute("id"));
	    System.out.println("vport out In port id is " + vportOut_In.getAttribute("id"));
	    System.out.println("filter In port id is " + filter_In.getAttribute("id"));
	    System.out.println("filter Out port id is " + filter_Out.getAttribute("id"));
	    System.out.println("simpleFW In port id is " + simpleFW_In.getAttribute("id"));
	    System.out.println("simpleFW Out port id is " + simpleFW_Out.getAttribute("id"));
	    System.out.println("attoLB In port id is " + attoLB_In.getAttribute("id"));
	    System.out.println("attoLB Out port id is " + attoLB_Out.getAttribute("id"));
	    
		int vportIn_Out_Width = (vportIn_Out.getSize().getWidth()/2);
        int vportIn_Out_Height = (vportIn_Out.getSize().getHeight())/2;
		int vportOut_In_Width = (vportOut_In.getSize().getWidth())/2;
        int vportOut_In_Height = (vportOut_In.getSize().getHeight())/2;
		int filter_In_Width = (filter_In.getSize().getWidth())/2;
        int filter_In_Height = (filter_In.getSize().getHeight())/2; 
		int filter_Out_Width = (filter_Out.getSize().getWidth())/2;
        int filter_Out_Height = (filter_Out.getSize().getHeight())/2;
		int simpleFW_In_Width = (simpleFW_In.getSize().getWidth())/2;
        int simpleFW_In_Height = (simpleFW_In.getSize().getHeight())/2;
		int simpleFW_Out_Width = (simpleFW_Out.getSize().getWidth())/2;
        int simpleFW_Out_Height = (simpleFW_Out.getSize().getHeight())/2;
		int attoLB_In_Width = (attoLB_In.getSize().getWidth())/2;
        int attoLB_In_Height = (attoLB_In.getSize().getHeight())/2;
        int attoLB_Out_Width = (attoLB_Out.getSize().getWidth())/2;
        int attoLB_Out_Height = (attoLB_Out.getSize().getHeight())/2;
        
        operation.moveToElement(vportIn_Out,vportIn_Out_Width+2,vportIn_Out_Height).clickAndHold().moveToElement(filter_In,filter_In_Width-2,filter_In_Height).build().perform();     
        Thread.sleep(2000);
        //operation.moveToElement(filter_Out,filter_Out_Width+2,filter_Out_Height).clickAndHold().moveToElement(simpleFW_In,simpleFW_In_Width-2,simpleFW_In_Height).build().perform();      
        //Thread.sleep(2000);
        //operation.moveToElement(simpleFW_Out,simpleFW_Out_Width+2,simpleFW_Out_Height).clickAndHold().moveToElement(attoLB_In,attoLB_In_Width-2,attoLB_In_Height).build().perform();  
        //Thread.sleep(2000);
        //operation.moveToElement(attoLB_Out,attoLB_Out_Width+2,attoLB_Out_Height).clickAndHold().moveToElement(vportOut_In,vportOut_In_Width-2,vportOut_In_Height).build().perform();;      
        //Thread.sleep(2000);
        
	    WebElement save = driver.findElement(By.xpath("//i[@class='fa fa-save']"));
	    save.click();
	    
	    Thread.sleep(3000);
	    */
	    
	    //WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(m_menu_service));
		//m_menu_service.click();
		WebElement s_menu_serviceList = driver.findElement(By.xpath("//*[contains(text(), 'Service List')]"));
		s_menu_serviceList.click();
		
		WebElement searchField = driver.findElement(By.xpath("//input[@placeholder='Search Services']"));
		searchField.sendKeys("atheneAutoTest");

		
		try {
			//assertEquals("Dropped", nsPanel.getText());
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
			}
		
	}
		


	@AfterTest
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
