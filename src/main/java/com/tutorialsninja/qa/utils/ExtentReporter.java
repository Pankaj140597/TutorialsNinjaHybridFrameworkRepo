package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() 
	{
		ExtentReports extentReport= new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkRepoter= new ExtentSparkReporter(extentReportFile);
		
		sparkRepoter.config().setTheme(Theme.DARK);
		sparkRepoter.config().setReportName("TutorialsNinja Test Automation Results Report");
		sparkRepoter.config().setDocumentTitle("TN Automation Report");
		sparkRepoter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkRepoter);
		
		Properties configProp=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try 
		{
			FileInputStream fisConfigProp= new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url") );
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("browserName") );
		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail") );
		extentReport.setSystemInfo("Password",configProp.getProperty("validPassword") );
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name") );
		extentReport.setSystemInfo("User Name",System.getProperty("user.name") );
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version") );
		
		return extentReport;
	}

}
