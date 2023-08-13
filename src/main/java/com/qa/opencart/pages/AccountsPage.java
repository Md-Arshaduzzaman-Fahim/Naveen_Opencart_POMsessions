package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
    private By header = By.xpath("//div[@id='logo']/a/img");
    private By accouuntSection = By.xpath("//div[@id='content']/h2");
    private By searchField = By.xpath("//input[@name='search']");
    private By searchButton = By.xpath("//div[@id='search']//button");
    private By logoutLink = By.linkText("Logout");
    
    
    private String t = "title";
    
    public AccountsPage(WebDriver driver) {
    	this.driver = driver;
    	eleUtil = new ElementUtil(driver);
    }
    
    
    public String getAccountPageTitle() {
    	return eleUtil.waitForTitleIsAndFetch(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
    }
    
    
    public String getAccountsPageHeader() {
    	return eleUtil.getElementAttribute(header, t);
    }
    
    
    public boolean isLogoutLinkExist() {
    	return eleUtil.doElementIsDisplayed(logoutLink);
    }
    
    
    
    public void logout() {
    	if(isLogoutLinkExist()) {
    		eleUtil.doClick(logoutLink);
    	}
    }
    
    
    public List<String> getAccountSecList() {
    	List<WebElement> accSecList = eleUtil.waitForElementsPresence(accouuntSection, 10);
    	List<String> accSecValList = new ArrayList<String>();
		for (WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
    	return accSecValList;
    }
    
    
    
    public List<String> getAccountSecList2(){
    	List<String> accSecList = eleUtil.getElementsTextList(accouuntSection);
    	return accSecList;
    }
    
    
    
    public boolean isSearchExist() {
    	return eleUtil.doElementIsDisplayed(searchField);
    }
    
    
    
    public SearchResultsPage doSearch(String productName) {
    	System.out.println("Searching the product: "+ productName);
    	eleUtil.doSendKeys(searchField, productName);
    	eleUtil.doClick(searchButton);
    	return new SearchResultsPage(driver);
    }
    
    
}
