package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"test11@gmail.com", "test@123"},
			{"fahimxzaman@gmail.com", "test@3421"},
			{"test11@gmail.com", "Yeagerxaman789858"},
			{"",""}
		};
	}
	
	@Test(priority=1, dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String eml, String pswrd) {
		Assert.assertFalse( loginpage.doLoginWithWrongCredentials(eml, pswrd));
	}
	
	
	
}
