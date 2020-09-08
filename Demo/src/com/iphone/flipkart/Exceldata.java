package com.iphone.flipkart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Exceldata {
	static int k = 1;

	public static void listofelements(List<WebElement> list, int j, WebDriver driver) throws IOException {
		for (int i = 0; i < list.size(); i++) {
			String Devicename = list.get(i).getText();
			String Customerrating = driver.findElement(By.xpath("//div[@class='hGSR34']")).getText();
			String Storagecapacity = driver.findElement(By.xpath("//ul[@class='vFw0gD']//li[1]")).getText();
			String price = driver.findElement(By.xpath("//div[@class='_1uv9Cb']//div")).getText();
			writeexceldata(k, j, Devicename);
			writeexceldata(k, j + 1, Customerrating);
			writeexceldata(k, j + 2, Storagecapacity);
			writeexceldata(k, j + 3, price);

			k++;
		}
	}

	public static void writeexceldata(int i, int j, String Data) throws IOException {

		File src = new File("S:\\Flipkart_iphone_data.xlsx");

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sh1 = wb.getSheetAt(0);

		XSSFRow row = sh1.getRow(i);

		if (row == null) {
			row = sh1.createRow(i);
		}

		XSSFCell cell = row.createCell(j);

		XSSFCell cell1 = row.createCell(0);

		cell1.setCellValue(i);

		cell.setCellValue(Data);

		FileOutputStream fout = new FileOutputStream(
				new File("S:\\Flipkart_iphone_data1.xlsx"));

		wb.write(fout);

		fout.close();

	}

	public static WebElement checkelement(By locator, WebDriver driver) {
		WebElement ele = null;
		try {
			ele = driver.findElement(locator);
			return ele;
		} catch (Exception e) {
			return null;
		}

	}

}
