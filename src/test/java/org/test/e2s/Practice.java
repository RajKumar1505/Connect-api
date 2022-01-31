package org.test.e2s;

import org.testng.annotations.Test;

import BaseClassPackage.BaseClass;

public class Practice extends BaseClass {
	@Test
	private void test123() {
		browserlaunchinmaven("chrome");
		urlLaunch("https://studentconnect-test.cardiff.ac.uk/");
		maximize();
		pageLoadWait();
		impwait();

		toClickByFluWait("xpath", "//div[@class='select-user-list-img staff']");
		toClickByFluWait("xpath", "//button[text()='Next']");

		toFillTextBoxByFluWait("id", "username", "raj");
		// toFillTextBoxByFluWait(locname, loc, value);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	
	
	
	}
}
