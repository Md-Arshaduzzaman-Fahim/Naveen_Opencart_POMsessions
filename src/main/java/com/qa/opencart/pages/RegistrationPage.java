package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline'][1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][2]/input");
	
	private By agreeCheckbox = By.xpath("//div[@class='pull-right']/input[@type='checkbox']");
	private By continueButton = By.xpath("//div[@class='pull-right']/input[@type='submit']");
	
	private By successMsg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckbox);
		eleUtil.doClick(continueButton);
		String msg = eleUtil.waitForElementVisible(successMsg, 5).getText();
		if(msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
}
