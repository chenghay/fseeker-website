package com.flowerseeker.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * Place for shared selenium logic
 * 
 * @author cliff
 */
public class AbstractSeleniumTest {

	protected Selenium selenium;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
	
	protected void login() {
		selenium.open("/flowerseeker/");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=j_username", "jdoe");
		selenium.type("name=j_password", "joe");
		selenium.click("css=button.btn.btn-primary");
		selenium.waitForPageToLoad("30000");
		Assert.assertTrue("Looks like login was unsuccessful as the Logout link is missing",
				selenium.isTextPresent("Logout"));
	}
}
