package com.interfaceelement;

public interface OpenMRSElements {

	// Login Page

	String username_xpath = "//input[@name='username']";
	String password_id = "password";
	String impatientWard_xpath = "//ul[@id = 'sessionLocation']/li[text() = 'Inpatient Ward']";
	String login_xpath = "//p/input[@id = 'loginButton']";

	// Home Page

	String registerPatient_xpath = "//a[text()[normalize-space()= 'Register a patient']]";

	// Register Patient Page

	String givenName_name = "givenName";
	String familyName_name = "familyName";
	String nextButton_xpath = "(//button[@type='button'])[3]";
	String gender_id = "gender-field";
	String birthDay_xpath = "//input[@name='birthdateDay']";
	String birthMonth_id = "birthdateMonth-field";
	String birthYear_xpath = "//p[@class='left']/input[@name='birthdateYear']";
	String addressFirst_xpath = "//input[@name='address1']";
	String addressSecond_xpath = "//input[@name='address2']";
	String postalCode_id = "postalCode";
	String phoneNumber_name = "phoneNumber";
	String relationShipType_id = "relationship_type";
	String personName_xpath= "//div[@id='relationship']//input";
	String confirmName_xpath = "//*[text() = 'Name: ']";
	String confirmButton_id = "submit";
	
}
