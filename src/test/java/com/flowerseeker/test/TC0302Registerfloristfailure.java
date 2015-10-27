package com.flowerseeker.test;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class TC0302Registerfloristfailure {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://localhost:8443/");
		selenium.start();
	}

	@Test
	public void testTC0302Registerfloristfailure() throws Exception {
		selenium.open("/flowerseeker/");
		selenium.click("link=Register");
		selenium.click("css=ul.dropdown-menu > li > a");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=storename", "hippo");
		selenium.type("id=address1", "777 vermont ave");
		selenium.type("id=city", "los angeles");
		selenium.select("id=state", "label=California");
		selenium.type("id=zipcode", "90007");
		selenium.type("id=storeEmail", "hippo@msn.com");
		selenium.type("id=paypal", "hippo@msn.com");
		selenium.type("id=phone", "310-459-6836");
		selenium.type("id=usernameInput", "sophyei");
		selenium.type("id=userphone", "310-948-7660");
		selenium.type("id=email", "hippo@hotmail.com");
		selenium.type("id=password", "hippo");
		selenium.type("id=confirmPassword", "hippo");
		selenium.click("css=button.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("css=div.alert.alert-error"));
		assertTrue(selenium.isTextPresent("There are errors in your input!"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
