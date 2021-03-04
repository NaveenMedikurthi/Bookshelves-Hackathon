package com.DisplayBookshelves.module;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.DisplayBookshelves.framework.base.BaseUI;
import com.aventstack.extentreports.Status;

public class TestUrbanLadder extends BaseUI {
	
	

	@BeforeClass
	public void setUp() {
		invokeBrowser("chrome");
		openURL("HomePageURL");
	}

	/************************************************************************/

	@Test
	public void displayDetailsOfFirstThreeBookshelves() {
		
		logStatusInfo("Test-1 Start --> Display Details of First 3 Bookshelves Test Started");
		
		assertequals(prop.getProperty("HomePageTitle"), driver.getTitle());
		alertkill();
		enterText("search_Id", "Bookshelves" + Keys.ENTER);

		// alertkill();
		// price
		actionMoveToElement("price_Xpath");
		actionDragDropBy("price_slider_Xpath", -210, 0);

		// storageType
		alertkill();
		actionMoveToElement("storagetype_Xpath");
		elementClick("storageTypeOpen_Xpath");

		// exclude
		elementClick("exclude_Xpath");

		//alertkill();

		// fetching first three bookshelves
		System.out.println("Printing The First 3 Bookshelves\n");

		System.out.println("Name: " + getTextValue("nameBookShelf1_Xpath"));
		System.out.println("Price: " + getTextValue("priceBookShelf1_Xpath").replaceAll(",", ""));

		System.out.println("Name: " + getTextValue("nameBookShelf2_Xpath"));
		System.out.println("Price: " + getTextValue("priceBookShelf2_Xpath").replaceAll(",", ""));

		System.out.println("Name: " + getTextValue("nameBookShelf3_Xpath"));
		System.out.println("Price: " + getTextValue("priceBookShelf3_Xpath").replaceAll(",", ""));
		
		logStatusInfo("Test-1 End --> Display Details of First 3 Bookshelves Test Ended");

	}

	/*****************************************************************************/

	@Test(priority=1)
	public void collections_SubMenuItemsDisplay() {

		logStatusInfo("Test-2 Start --> Collections_SubMenu Items Display Test Started");
		
		assertequals(prop.getProperty("HomePageTitle"), driver.getTitle());

		// Navigating to Home Page
		navigateToURL("HomePageURL");

		// Closing the login pop-up
		// elementClick("close_loginPopUp_Xpath");

		// Clicking on collection tab
		elementClick("collectionTab_Xpath");

		// Printing furniture options under Being At Home Tab
		List<WebElement> subMenu = getElements("collection_BeingAtHome_Xpath");
		System.out.println("\nPrinting Being At Home Sub-Menu Items\n");
		int i = 1;
		for (WebElement element : subMenu) {
			waitUntillElementIsVisible(element);
			System.out.println(i++ + ". " + element.getText());
		}
		System.out.println("\n");
		logStatusInfo("Test-2 End --> Collections_SubMenu Items Display Test Ended");
		
	}

	/********************************************************************************/

	@Test(priority=2)
	public void giftCards_WithInvalidDetails() {

		logStatusInfo("Test-3 Start --> Gift Cards With Invalid Details Test Started");

		assertequals(prop.getProperty("HomePageTitle"), driver.getTitle());
		
		// Navigating to Home Page
		navigateToURL("HomePageURL");

		// to close the login window
		// elementClick("close_loginPopUp_Xpath");

		//// Clicking on Gift Cards
		elementClick("clickOnGiftCard_Xpath");

		// Clicking on Birthday/Anniversary
		elementClick("birthdayAnniversaryCard_Xpath");

		// Entering the amount
		enterText("cardAmount_Xpath", "1500");

		// Clicking on Next Button
		elementClick("clickNext_Xpath");

		// Entering the details with invalid customer email and clicking on submit
		enterTextFromTestData("recepientsName_Name", "RecepientName");
		enterTextFromTestData("customer_name_Name", "CustomerName");
		enterTextFromTestData("recepientEmail_Name", "RecepientEmail");
		enterTextFromTestData("customerEmail_Name", "CustomerEmail");
		enterTextFromTestData("customerMobileNumber_Name", "CustomerMobileNo");
		enterTextFromTestData("message_Name", "MessageDescription");
		elementClick("submitButton_Xpath");

		// Taking screenshot of the error message displayed on tooltip
		takeScreenShotOnFailure();

		logStatusInfo("Test-3 End --> Gift Cards With Invalid Details Test Ended");
	}

	/****************************************************************************/

	@AfterClass
	public void afterTest() {
		quitBrowser();
		report.flush();
	}
}
