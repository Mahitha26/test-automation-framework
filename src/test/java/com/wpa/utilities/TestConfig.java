package com.wpa.utilities;

public class TestConfig {

	public static String server = "smtp.gmail.com";
	public static String from = "";
	public static String password = "";
	public static String[] to = {"example@abc.com"};
	public static String subject = "Test Report";
	public static String messageBody = "Hello, we have a new test report";
	public static String attachmentPath = System.getProperty("user.dir") + "/target/surefire-reports/html/extent.html";
	public static String attachmentName = "extent.html";

}
