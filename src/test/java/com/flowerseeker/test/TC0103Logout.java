package com.flowerseeker.test;

import org.junit.Assert;
import org.junit.Test;

public class TC0103Logout extends AbstractSeleniumTest {

	@Test
	public void testTC0103Logout() throws Exception {
		login();
		selenium.open("/flowerseeker/");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		Assert.assertTrue("Looks like logout was unsuccessful as the login link is missing",
				selenium.isTextPresent("Login"));
	}
}
