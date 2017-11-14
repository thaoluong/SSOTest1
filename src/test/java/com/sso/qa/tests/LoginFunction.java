package com.sso.qa.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

// Import package pageObject.*

import com.sso.qa.pages.*;
import com.sso.qa.utility.*;

public class LoginFunction {

	private static WebDriver driver = null;
	ExcelDataConfig file = new ExcelDataConfig("D:\\Login.xls");

	@BeforeMethod
	public static void setUp() {

		System.setProperty("webdriver.chrome.driver", "/Users/thaoluonghong/Documents/Webdriverjav/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://sso-demo.ntq.solutions");
	}

//	@Test
//	public static void LoginSuccess() {
//		// Use page Object library now
//
//		LogInPage.txtbx_UserName(driver).sendKeys("admin");
//
//		LogInPage.txtbx_Password(driver).sendKeys("admin");
//
//		LogInPage.btn_LogIn(driver).click();
//
//		System.out.println(" Login Successfully, now it is the time to Log Off buddy.");
//
//		driver.quit();
//
//	}
	@Test
	public void loginUnsuccess() throws IOException{
		for (int i = 1; i <= 5; i++) {
//			String user = com.sso.qa.utility.GetCellToString.getCellValue(file.getSheet(0).getRow(i).getCell(1));
//			String pass = com.sso.qa.utility.GetCellToString.getCellValue(file.getSheet(0).getRow(i).getCell(2));
//			Cell resultCell = file.getSheet(0).getRow(i).createCell(4);
			String user = file.getSheet(0).getRow(i).getCell(1).getStringCellValue();
			String pass = file.getSheet(0).getRow(i).getCell(2).getStringCellValue();
			Cell resultCell = file.getSheet(0).getRow(i).createCell(4);
			
			LogInPage.txtbx_UserName(driver).sendKeys(user);
			LogInPage.txtbx_Password(driver).sendKeys(pass);
			LogInPage.btn_LogIn(driver).click();
			try {
				LogInPage.btn_LogIn(driver).click();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (LogInPage.getErrMessage(driver).equals("aaa")) {
				resultCell.setCellValue("PASSED");
			} else {
				resultCell.setCellValue("FAILED");
			}
			System.out
					.println("Row " + i + " - Username: " + user + " / Pass: " + pass + "  >>> Result: " + resultCell);

		}

		driver.close();
		FileOutputStream outFile = new FileOutputStream(new File("D:\\LoginUnsuccess.xls"));
		com.sso.qa.utility.ExcelDataConfig.wb.write(outFile);
		outFile.close();
	}

}