package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	@BeforeClass
	public void setupRegistration() {
		registrationpage = loginpage.goToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random randomGenarator = new Random();
		String email = "randomtestemail"+randomGenarator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	
	@Test( dataProvider="getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationpage.accountRegistration( firstName,  lastName,  getRandomEmail(),  telephone,  password,  subscribe));
	}
	
	
	
	
}
