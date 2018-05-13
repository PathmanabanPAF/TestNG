package com.paf.scripts;


import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import bsh.Capabilities;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ietest {
	@Test()
	public void test() throws IOException, RowsExceededException, WriteException, InterruptedException
	{
			// TODO Auto-generated method stub

			///WebDriver driver = new FirefoxDriver();
		    WebDriver driver;
		   
			System.setProperty("webdriver.ie.driver", "J:\\IEDriverServer.exe");
					Thread.sleep(3000);
					
			//driver=new InternetExplorerDriver();

			//System.setProperty("webdriver.chrome.driver","S:\\PEGA\\PAF_QA\\Automation\\PAF\\PAF_Workspace\\Driver\\chromedriver.exe");
			//driver=new ChromeDriver();
	
			 driver = new InternetExplorerDriver(); 
			//	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             
			try {
				driver.get("http://phqsltcllsqa001.patientadvocate.org:8080/prweb/PRServlet/DGUM90lACED74DAWt5QdLQ%5B%5B*/!STANDARD?");
				Thread.sleep(4000);
				driver.findElement(By.id("txtUserID")).sendKeys("PAF848");
				driver.findElement(By.id("txtPassword")).sendKeys("rules");
				driver.findElement(By.id("sub")).click();
				Thread.sleep(4000);
				driver.findElement(By.linkText("Logout"));
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Handling PAF LLS-QA URL");
			}
		
			
			Thread.sleep(3000);
	       
			//driver.findElement(By.id("email")).sendKeys("pathmanaban.g@rediffmail.com");
			driver.close();
	}
}

