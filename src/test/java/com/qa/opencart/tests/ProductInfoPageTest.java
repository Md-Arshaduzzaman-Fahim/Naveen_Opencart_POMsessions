package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeTest
	public void productInfoSetup() {
		accountspage = loginpage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void productHeaderTest() {
		searchresultspage = accountspage.doSearch("MacBook");
		productinfopage = searchresultspage.selectProduct("MacBook Pro");
		Assert.assertEquals(productinfopage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority=2)
	public void productImagesCountTest() {
		searchresultspage = accountspage.doSearch("iMac");
		productinfopage = searchresultspage.selectProduct("iMac");
		Assert.assertEquals(productinfopage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	
	@Test(priority=3)
	public void productInfoTest() {
		searchresultspage = accountspage.doSearch("MacBook");
		productinfopage = searchresultspage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productinfopage.getProductInfo();
		actProductInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
	
	
}
