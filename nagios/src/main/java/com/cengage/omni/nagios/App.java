package com.cengage.omni.nagios;

/**
 * Hello world!
 *body>.status>tbody>tr>td:nth-child(4)
 *
 *
 *1. ask for test duration.
 *2. Ask for frequency of screenshot capturing.
 */

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import javax.swing.JOptionPane;

import org.openqa.selenium.*;

import com.cengage.omni.nagios.FileLoader;
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	File dirPath=FileLoader.getFileLocation();
    	String testDuration= null;
    	String frequency= null;
		if(dirPath == null)
    	{
    		FileLoader.getErrorMain();
    		System.exit(0);
    	}
    	else
    		{
    		   testDuration = JOptionPane.showInputDialog("Please enter the duration of test in seconds");
    		   if(testDuration.equals(""))
    	    	 testDuration = "7200";  //2 hour 
    		   JOptionPane.showMessageDialog(null, "The test duration is :"+testDuration+" seconds");
    		   frequency = JOptionPane.showInputDialog("Please enter the frequency of test check in seconds");
   			   if(frequency.equals(""))
   	    	     frequency = "300";  //5 min
   	    	   JOptionPane.showMessageDialog(null, "The frequency is :"+frequency+" seconds");
   	    	}
    	
    	FirefoxProfile ffProfile=new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffProfile);
		driver.get("URL");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		int tempTime=0;
		while(tempTime<=Integer.parseInt(testDuration))
		{   
			try{
				System.out.println(tempTime);
				CheckAndSave.checkAndSave(driver, dirPath);
				driver.switchTo().defaultContent();
				Thread.sleep(Integer.parseInt(frequency)*1000);
				tempTime+=Integer.parseInt(frequency);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Mission aborted! Following error occured "+ex);
				System.exit(0);
			}
			
		}
		driver.quit();
		}
}
