package com.test.automation.uiAutomation.addToCart;

import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.AddToCartPage;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.ProductDetailsPage;

public class TC002_VerifyFacebookLink extends TestBase{
	
	HomePage homepage;
	ProductDetailsPage productDetailsPage;
	AddToCartPage addToCart;
	
	@BeforeClass
	public void setUp() {
      init();
	}

	@Test
	public void verifyFacebookLink() {
		log.info("=======Starting verifyFacebookLink test========");
		homepage = new  HomePage(driver);
		homepage.switchToFrame();
		homepage.clickOnNavigationMenu(homepage.mens);
		homepage.clickOnProductInMensSection(homepage.jackets);
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.selectProduct(productDetailsPage.contrast_jacket);
		addToCart = new AddToCartPage(driver);
		addToCart.clickOnfacebookLink();
		
		// This code will get all windows ID and Based on window ID we can move to parent or child window.
		Iterator<String> itr = getAllWindows();
		
		String parentWindow = itr.next();
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		boolean status = addToCart.verifyFaceBookMessage();
		driver.switchTo().window(parentWindow);
		
		Assert.assertEquals(true, status);
		
		homepage.switchToDefaultContent();
		
		log.info("=======Finished verifyFacebookLink test========");
	}

	@AfterClass
	public void endTest() {
      closeBrowser();
	}

}