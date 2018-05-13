package com.paf.wrappermethods;



import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Excelmethods {
	WritableWorkbook rwb;
	WritableSheet rws;
	
public void createworkbook() throws IOException
{
	rwb=Workbook.createWorkbook(new File("D:\\Testleaf\\Excel\\pathan.xls"));
	rws=rwb.createSheet("Report", 0);
}

public void closeworkbook() throws IOException, WriteException
{
	rwb.write();
	rwb.close();
}
public void addheader() throws RowsExceededException, WriteException
{
	Label L1=new Label(0,0,"S.NO");
	Label L2 = new Label(1, 0, "TC Name");
	Label L3 = new Label(2, 0, "Description");
	Label L4 = new Label(3, 0, "Results");

	rws.addCell(L1);
	rws.addCell(L2);
	rws.addCell(L3);
	rws.addCell(L4);
}

/*public void addblank() throws RowsExceededException, WriteException
{
	Label L1=new Label(0,0,"");
	Label L2 = new Label(1, 0, "");
	Label L3 = new Label(2, 0, "");
	Label L4 = new Label(3, 0, "");

	rws.addCell(L1);
	rws.addCell(L2);
	rws.addCell(L3);
	rws.addCell(L4);
}

public void adduser() throws RowsExceededException, WriteException
{
	Label L1=new Label(0,0,"User1");
	Label L2 = new Label(1, 0, "");
	Label L3 = new Label(2, 0, "");
	Label L4 = new Label(3, 0, "Results");

	rws.addCell(L1);
	rws.addCell(L2);
	rws.addCell(L3);
	rws.addCell(L4);
}*/

public void reportstep(String Tc, String des,String result) throws RowsExceededException, WriteException
{
	Label sno=new Label(0,rws.getRows(),""+rws.getRows());
	Label testcase=new Label(1,rws.getRows(),Tc);
	Label description=new Label(2,rws.getRows(),des);
	Label result1=new Label(3,rws.getRows(),result);
	
	//System.out.println(rws.getRows());
	
	rws.addCell(sno);
	rws.addCell(testcase);
	rws.addCell(description);
	rws.addCell(result1);
	
}

public void openworkbook()
{
	
}

}


