package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0204Searchbyrating {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0204Searchbyrating() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=Review Rating");
		selenium.click("//li[2]/a/i[3]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Cherry Blossom"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
