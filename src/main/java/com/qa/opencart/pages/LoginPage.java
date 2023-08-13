package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By email = By.name("email");
	private By pass = By.name("password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	@Step("getting login page title.....")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	@Step("checking forgot password exist or not.....")
	public boolean isForgotPsswdLinkExist() {
		return eleUtil.doElementIsDisplayed(forgotPasswordLink);
	}
	
	@Step("checking register link exist or not.....")
	public boolean isRegisterLinkExist() {
		return eleUtil.doElementIsDisplayed(registerLink);
	}
	
	
	@Step("do login with username:{0} and password:{1}")
	public AccountsPage doLogin(String eml, String pswrd) {
		System.out.println("login with: "+ eml +": "+ pswrd);
		eleUtil.doSendKeys(email, eml);
		eleUtil.doSendKeys(pass, pswrd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	
	@Step("do login with wrong username:{0} and wrong password:{1}")
	public boolean doLoginWithWrongCredentials(String eml, String pswrd) {
		System.out.println("try to login with : " + eml +" : " + pswrd);
		eleUtil.doSendKeys(email, eml);
		eleUtil.doSendKeys(pass, pswrd);
		eleUtil.doClick(loginBtn);
		String errorMsg = eleUtil.doElementGetText(loginErrorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("login is not successful.......");
			return false;
		}
		return true;
	}
	
	
	@Step("navigating to registration page....")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
}
