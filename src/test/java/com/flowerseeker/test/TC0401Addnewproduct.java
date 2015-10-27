package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0401Addnewproduct {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0401Addnewproduct() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=[Manage Store]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Add Product");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=name", "Roses");
		selenium.type("id=description", "cherry flower");
		selenium.type("id=price", "30.00");
		selenium.type("id=file", "C:\\Users\\Sophia\\Desktop\\usc\\CSCI577B\\project design\\flower template\\flower1.jpg");
		selenium.click("//button[@type='button']");
		selenium.click("css=input[type=\"checkbox\"]");
		selenium.click("//form[@id='addProductForm']/fieldset/div[5]/div/div/ul/li[4]/a/label");
		selenium.click("//input[@value='Wedding']");
		selenium.click("css=div.form-actions");
		selenium.click("css=button.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Roses"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
