package com.wpa.testcases;

import org.testng.annotations.Test;

import com.wpa.base.TestBase;

public class Registration extends TestBase {

	@Test(priority = 1)
	public void Register() throws InterruptedException {

		log.debug("Inside Registration box");
		Thread.sleep(3000);
		click("RegisterLink");


	}

	
	

}
