package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		searchPage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		
		//HomePage homePage=new HomePage(driver);
		//homePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("validProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		
		 //searchPage = homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
		//SearchPage searchPage=new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in search results");
		//Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product HP is not displayed in search results");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct()
	{
		searchPage=homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
		//HomePage homePage=new HomePage(driver);
		//homePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		
		//searchPage=homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
		//SearchPage searchPage=new SearchPage(driver);
		String actualSearchMessage=searchPage.retriveNoProductMessageText();
		//String actualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search results is not displated");
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct()
	{
		
		//HomePage homePage=new HomePage(driver);
		searchPage=homePage.clickOnSearchButton();
		//driver.findElement(By.name("search")).sendKeys("");
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
//		SearchPage searchPage=new SearchPage(driver);
		String actualSearchMessage=searchPage.retriveNoProductMessageText();
		//String actualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search results is not displated");
	}
	
	
	

}
