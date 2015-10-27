package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0803checkoutfailure {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0803checkoutfailure() throws Exception {
		selenium.open("/flowerseeker/cart");
		selenium.click("link=FlowerSeeker");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=[Cart]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=div.well"));
		selenium.click("css=a.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=p"));
		selenium.type("id=address1", "777 vermont ave");
		selenium.type("id=city", "los angeles");
		selenium.select("id=usState", "label=California");
		selenium.type("id=zipCode", "90007");
		selenium.click("css=button.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=td"));
		selenium.click("link=[Cart]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=div.alert.alert-success"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
