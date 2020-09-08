package com.iphone.flipkart;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Flipkart extends Exceldata {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		

		By message = By.xpath("//div[@class='_1lC95o']");

	//	System.setProperty("webdriver.chrome.driver","S:\\SelniumABC\\chromedriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();

		driver.get("https://www.flipkart.com/");
		Actions actions=new Actions(driver);
		  Action sendEsc=actions.sendKeys(Keys.ESCAPE).build();
		  sendEsc.perform();

	/*	driver.findElement(By.xpath("//input[@class='_2zrpKA _1dBPDZ']")).sendKeys("UserName");
		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']")).sendKeys("Password");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
*/
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"))
				.sendKeys("Iphone mobile");
		driver.findElement(By.xpath("//button[@class='vh79eN']")).click();

		JavascriptExecutor je = (JavascriptExecutor) driver;

		Thread.sleep(2000);

		// driver.findElement(By.xpath("//section[@class='_1MCcAi D_NGuZ']"));
		Select sel = new Select(driver.findElement(By.xpath("//div[@class='_1YoBfV']//select[@class='fPjUPw']")));
		sel.selectByVisibleText("₹50000");

		Thread.sleep(2000);
		String pages = driver.findElement(By.xpath("//div[@class='_2zg3yZ']//span")).getText();
		String[] p1 = pages.split("Page 1 of ");
		String pagecount = p1[1];
		// System.out.println(pagecount);
		int Total = Integer.parseInt(pagecount);

		for (int i = 1; i <= Total; i++) {
			List<WebElement> list = driver.findElements(
					By.xpath("//div[@class='_1UoZlX']//div[contains(@class,'_1-2Iqu')]//div[@class='_3wU53n']"));

			listofelements(list, 1, driver);
			
			if (i < Total) {
				je.executeScript("window.scrollBy(0,6900)");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(1000);
			}

			System.out.println(i + " page record is collected");

			if (checkelement(message, driver) != null) {
				// driver.findElement(By.xpath("//button[text()='RETRY']")).click();
				// Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(3000);
			}
		}
driver.close();
		driver.quit();

	}
}