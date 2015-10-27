package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0202Searchbyprice {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0202Searchbyprice() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=Price");
		selenium.click("link=$50-100");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("$80.0"));
		assertTrue(selenium.isTextPresent("penguin"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
