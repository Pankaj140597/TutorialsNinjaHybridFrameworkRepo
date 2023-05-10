package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;
	
	public LoginTest()
	{
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homePage=new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
		
//		hard coding-->
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
//		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1,dataProvider = "validCredentialsSuppliers")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		
		AccountPage accountPage = loginPage.login(email, password);
		
		//loginPage.enterEmailAddress(email);
		//driver.findElement(By.id("input-email")).sendKeys(email);
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		
		//loginPage.enterPassword(password);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		
		//AccountPage accountPage=loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		//AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit Your Account Information option is not displayed");
		
	}
	
	@DataProvider(name="validCredentialsSuppliers")
	public  Object[][] supplyTestData()
	{
		Object[][] data= {{"right2thinkpvb@gmail.com","12345"},
				{"pankajborkaar@gmail.com","12345"},
				{"pankaajborkar@gmail.com","12345"}};
		
//		Object[][] data=Utilities.getTestDataFromExcel("Login");
	return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{	
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		
		//loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		
		//loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotWarningMessageText();
		//String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		
		String expectedWarningMsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "Expected Warning message is not displayed");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{		
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		
//		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//		loginPage.enterPassword(prop.getProperty("validPassword"));
//		loginPage.clickOnLoginButton();
		
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotWarningMessageText();
		//String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		String expectedWarningMsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "Expected Warning message is not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInalidPassword()
	{
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		//loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		
		//loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotWarningMessageText();
		//String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		String expectedWarningMsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "Expected Warning message is not displayed");
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{
//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys("");
		
		
		loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotWarningMessageText();
		//String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		String expectedWarningMsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "Expected Warning message is not displayed");
		
	}
	
	

}
