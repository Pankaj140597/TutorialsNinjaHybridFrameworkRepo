package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage ;
	
	public RegisterTest()
	{
		super();
	}
	
	public WebDriver driver;
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homePage=new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();
		
		//homePage.clickOnMyAccount();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		
		//registerPage = homePage.selectRegisterOption();
		//driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountSuccessPage=registerPage.registerWithMandotoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		//registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		
		//registerPage.enterLastName(dataProp.getProperty("lastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		
		//registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		
		//registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		
		//registerPage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		
		//registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		
		//registerPage.selectPrivacyPolicy();
		//driver.findElement(By.name("agree")).click();
		
		//accountSuccessPage = registerPage.clickOnContinueButton();
		
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		//verify
		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccesspageHeading();
		//String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not Displayed");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{	
		accountSuccessPage=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
//		RegisterPage registerPage=new RegisterPage(driver);
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		
		//registerPage.selectYesNewsLetterOption();
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
		//registerPage.selectPrivacyPolicy();
		//accountSuccessPage=registerPage.clickOnContinueButton();
		
		//verify
		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccesspageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not Displayed");
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{		
		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		
//		RegisterPage registerPage=new RegisterPage(driver);
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
		
		//registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		
		//registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		
		//registerPage.enterPassword(prop.getProperty("validPassword"));
		//registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		
		//registerPage.selectYesNewsLetterOption();
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
		//registerPage.selectPrivacyPolicy();
		//registerPage.clickOnContinueButton();
		
		String actualWarning=registerPage.retrieveDuplicateEmailAddressWarning();
		//String actualWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Message Regarding Duplicate Email Address is not Displayed.");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{	
		
		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyPolicyWarning=registerPage.retrievePrivacyPolicyWarning();
		//String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy Warning Message is not Displayed");
		
		String actualFirstNameWarning=registerPage.retrieveFirstNameWarning();
		//String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"First Name Warning message is not displayed");
		
		String actualLastNameWarning=registerPage.retrieveLastNameWarning();
		//String actualLastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"),"Last Name Warning message is not displayed");
		
		String actualEmailWarning=registerPage.retrieveEmailWarning();
		//String actualEmailWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("emailWarning"),"E-Mail Address Warning message is not displayed");
		
		String actualTelephonelWarning=registerPage.retrieveTelephoneWarning();
		//String actualTelephonelWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephonelWarning,dataProp.getProperty("telephoneWarning"),"Telephone Warning message is not displayed");
		
		
		String actualPasswordlWarning=registerPage.retrievePasswordWarninng();
		//String actualPasswordlWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordlWarning,dataProp.getProperty("passwordlWarning"),"Password Warning message is not displayed");
		
	}
	

}
