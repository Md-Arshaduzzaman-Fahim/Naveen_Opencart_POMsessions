package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productTitle = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']/li/a/img"); 
	private By productMetaData = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.name("quantity");
	private By addToCartBtn = By.xpath("//button[@id='button-cart']");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getProductHeader() {
		return eleUtil.doElementGetText(productTitle);
	}
	
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, 10).size();
		
	}
	
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}
	
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e: metaDataList) {
			String text = e.getText();
			String meta[] =	text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exP[] = metaPriceList.get(1).getText().trim().split(":");
		String exPrice = exP[1].trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exPrice", exPrice);
	}
	
	
	
}
