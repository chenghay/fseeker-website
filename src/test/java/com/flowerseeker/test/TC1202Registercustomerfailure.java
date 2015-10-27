package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC1202Registercustomerfailure {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC1202Registercustomerfailure() throws Exception {
		selenium.click("link=Register");
		selenium.click("//a[contains(text(),'Customer\n									Register')]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=customerFirstName", "Joanne");
		selenium.type("id=customerLastName", "Kao");
		selenium.type("id=customerPhonenumber", "321-321-4321");
		selenium.type("id=customerUsername", "joan");
		selenium.type("id=customerPassword", "2");
		selenium.type("id=customerRepeatPassword", "2");
		selenium.click("css=button.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Login"));
		assertTrue(selenium.isTextPresent("Email cannot be empty"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
