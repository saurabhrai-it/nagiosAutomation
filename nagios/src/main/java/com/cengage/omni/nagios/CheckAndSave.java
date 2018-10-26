package com.cengage.omni.nagios;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CheckAndSave {
 static void checkAndSave(WebDriver driver, File dirPath){
	 driver.switchTo().frame(driver.findElement(By.name("side")));
		WebElement quickSearch=driver.findElement(By.name("host"));
		quickSearch.click();
		quickSearch.sendKeys("*.stage*");
		quickSearch.sendKeys(Keys.RETURN);
		quickSearch.clear();
		driver.switchTo().defaultContent();
//		Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.name("main")));
		Select limit = new Select(driver.findElement(By.name("limit")));
		limit.selectByValue("0");
		List <WebElement> duration=driver.findElements(By.xpath("html/body/table[3]/tbody/tr[*]/td[5]"));
		List <WebElement> status=driver.findElements(By.xpath("html/body/table[3]/tbody/tr[*]/td[3]"));
		List <WebElement> service=driver.findElements(By.xpath("html/body/table[3]/tbody/tr[*]/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td"));
//		List <WebElement> host=driver.findElements(By.xpath("html/body/table[3]/tbody/tr[*]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/a"));
//		System.out.println(duration.size());
//		System.out.println(status.size());
//		System.out.println(service.size());
//		System.out.println(host.size());
		for(int i=0;i<duration.size();i++)
		{   
			String tempData = duration.get(i).getText();
			String tempStatus = status.get(i).getText();
//			String getService = service.get(i).getText();
			WebElement getServiceWeb = null;
			if(i>15 && i <duration.size()-15)
				getServiceWeb = service.get(i-15);
			else
			    getServiceWeb = service.get(i);
			String pattern ="(.+?)d (.+?)h.+s";
			String hours = null ;
			String day = null;
			Pattern r = Pattern.compile(pattern);   
			Matcher m = r.matcher(tempData);
	        if (m.find())
	          {
	        	day = m.group(1);
	        	hours = m.group(2);
	          }
			if(Integer.parseInt(hours)<8 && Integer.parseInt(day)==0 && tempStatus.equals("CRITICAL"))   //Integer.parseInt(hours)<8 && Integer.parseInt(day)==1 && 
			{   
				Actions action = new Actions(driver);
				action.moveToElement(getServiceWeb).build().perform();
				ScreenShot.takeScreenShot(dirPath, driver);
			}
		}
 }
}
