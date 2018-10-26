package com.cengage.omni.nagios;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
      static void takeScreenShot(File dest, WebDriver d){
    	   try{
	            File screenshot = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(screenshot,new File(dest+"\\screenshot"+System.currentTimeMillis()+".jpeg"));
	        }catch(Exception e){
	            System.out.println("Failure to take screenshot "+e);

	        }
       }
}
