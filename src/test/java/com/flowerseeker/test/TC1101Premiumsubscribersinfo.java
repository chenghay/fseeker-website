package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC1101Premiumsubscribersinfo {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC1101Premiumsubscribersinfo() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=[Manage Store]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Subscription");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button.btn");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=td"));
		assertTrue(selenium.isTextPresent("You're currently subscribed!"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
