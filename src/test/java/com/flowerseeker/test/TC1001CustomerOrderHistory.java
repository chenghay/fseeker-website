package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC1001CustomerOrderHistory {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC1001CustomerOrderHistory() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=[sophiawu]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Orders and Purchased Products");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=td"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
