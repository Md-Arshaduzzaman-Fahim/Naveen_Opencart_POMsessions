package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open cart app - Login Page")
@Story("US 101: open cart Login Design with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("Log in Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle =  loginpage.getLoginPageTitle();
		 Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Forgot password link Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageForgotpsswdLinkTest() {
		Assert.assertTrue(loginpage.isForgotPsswdLinkExist());
	}
	
	@Description("Register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void loginPageRegisterLinkTest() {
		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}
	
	@Description("Log in Test with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
	    accountspage = loginpage.doLogin(prop.getProperty("email").trim(), prop.getProperty("password").trim());
	    
		Assert.assertEquals(accountspage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE); 
		
	}
}
