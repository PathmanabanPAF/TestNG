package com.paf.scripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Filetesting {

	

@Test()
public void Filetest() throws IOException, InterruptedException
{

	WebDriver driver;
	driver= new FirefoxDriver();

	driver.get("http://phqswsqlrep001/Reports/Pages/Folder.aspx");	
	
	Thread.sleep(2000);
	Runtime.getRuntime().exec("F:\\PEGA\\PAF_QA\\Automation\\PAF\\PAF_Workspace\\PAF_Library\\reporttest.exe");
	Thread.sleep(2000);
	
	System.out.println("Report portal logged in");
}
}
