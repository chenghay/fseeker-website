package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0203Searchbyoccasion {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0203Searchbyoccasionn() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=Occasion");
		selenium.click("link=Christmas");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("penguin"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
