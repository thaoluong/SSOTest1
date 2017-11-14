package com.sso.qa.pages;

import org.openqa.selenium.*;

public class LogInPage {

	private static WebElement element = null;

	public static WebElement txtbx_UserName(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		return element;
	}

	public static WebElement txtbx_Password(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}

	public static WebElement btn_LogIn(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@type='submit']"));
		return element;

	}

	public static WebElement getErrMessage(WebDriver driver) {
		element = driver.findElement(By.xpath("//ngb-alert/div/div"));
		return element;
	}

}