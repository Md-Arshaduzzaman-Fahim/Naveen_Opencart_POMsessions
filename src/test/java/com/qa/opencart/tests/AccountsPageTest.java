package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;
import com.qa.opencart.utils.ExcelUtil;


public class AccountsPageTest extends BaseTest {
	
	@BeforeTest
	public void accPageSetup() {
		accountspage = loginpage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
		
	}
	
	
	@Test(priority =1)
	public void accPageTitleTest() {
		String actTitle = accountspage.getAccountPageTitle();
		System.out.println("acc page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	
	@Test(priority =2)
	public void accPageHeaderTest() {
		String header = accountspage.getAccountsPageHeader();
	    System.out.println("acc page header is: " + header);
	    Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MSG);
	    
	} 
	
	@Test(priority =3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountspage.isLogoutLinkExist());
	}
	
	
	@Test(priority =4)
	public void accPageSectionTest() {
		List<String> actAccSecList = accountspage.getAccountSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	
	@Test(priority =5, dataProvider= "productData")
	public void searchTest(String productName) {
		searchresultspage = accountspage.doSearch(productName);
		Assert.assertTrue(searchresultspage.getProductListCount()>0);
		
	}
	
	
	@DataProvider
	public Object[][] productSelectData() {
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	
	
	@Test(priority =6, dataProvider = "productSelectData" )
	public void selectProductTest(String productName, String mainProductName) {
		searchresultspage = accountspage.doSearch(productName);
		productinfopage = searchresultspage.selectProduct(mainProductName);
		Assert.assertEquals(productinfopage.getProductHeader(), mainProductName);
	}
	
	
}
