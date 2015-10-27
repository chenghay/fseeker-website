package com.flowerseeker.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TC0201Searchbylocation extends AbstractSeleniumTest {

	@Test
	public void testTC0201Searchbylocation() throws Exception {
		login();
		selenium.open("/flowerseeker/");
		selenium.click("link=Location");
		selenium.type("name=zip", "90007");
		selenium.click("css=button.btn.pull-right");
		selenium.waitForPageToLoad("30000");
		selenium.open("/flowerseeker/searchLocation?zip=90272");
		assertTrue(selenium.isTextPresent("Cherry Blossom"));
	}
}
