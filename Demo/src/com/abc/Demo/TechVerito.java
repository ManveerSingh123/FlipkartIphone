package com.abc.Demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TechVerito {
	public static void main(String args []) throws InterruptedException
	{
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 WebDriverWait waitElement=new WebDriverWait(driver,20);
		 
		 driver.get("https://manveer-singh.herokuapp.com/admin");
			String title=driver.getTitle();      //GET TITLE
			System.out.println(title);
			if(title.equals("Login | Qa Test")) {
				System.out.println("Correct title");}
			else {
				System.out.println("In Correct title");
				}
			driver.findElement(By.id("admin_user_email")).sendKeys("admin@example.com");
			driver.findElement(By.id("admin_user_password")).sendKeys("password");
			driver.findElement(By.name("commit")).click();
			
			driver.findElement(By.linkText("Products")).click();   //click on Pdocuts
			driver.findElement(By.linkText("Create one")).click(); //create pdocut
			
			driver.findElement(By.id("product_title")).sendKeys("Manveer-Singh-HeroKuApp");
			driver.findElement(By.id("product_sku")).sendKeys("HeroKuApp");
			driver.findElement(By.id("product_description")).sendKeys("Welcome to HeroKuApp");
			driver.findElement(By.name("commit")).click();//   submit the button
			
			waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("filters_sidebar_section"))).click(); // ExplictWait
			driver.switchTo().frame("filters_sidebar_section");    

			WebElement dropdown1=driver.findElement(By.xpath("//div[@id='q_title_input']//select"));  //Title dropDown
			Select sel=new Select(dropdown1);
			sel.deselectByVisibleText("Equals");
			
			driver.findElement(By.id("q_title")).sendKeys("Equals");
			
			WebElement firstEle=sel.getFirstSelectedOption();
			System.out.println("first element ="+firstEle.getText());
			
			List<WebElement> listOptions=sel.getOptions();
			for(WebElement webElement : listOptions)
			{
				System.out.println("options -"+webElement.getText());
			}
			
		Select sku=new Select(driver.findElement(By.xpath("//div[@id='q_sku_input']//select"))); //Select the Sku
			sku.selectByVisibleText("Starts with");
			
			driver.findElement(By.id("q_sku")).sendKeys("Starts with");
			
			
			Select desc=new Select(driver.findElement(By.xpath("//div[@id='q_description_input']//select"))); //Select the Description
			desc.selectByVisibleText("Ends with");
			
			driver.findElement(By.id("q_description")).sendKeys("Ends with");
			
		driver.findElement(By.id("q_created_at_gteq_datetime")).click();   //Created At clander from
		driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-highlight ui-state-active']")).click();
		
		driver.findElement(By.id("q_created_at_lteq_datetime")).click();   //Created At clander To
		driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).click();
		
		driver.findElement(By.id("q_updated_at_gteq_datetime")).click();  //Update Date Calendr from
		driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-highlight']")).click();
		
		driver.findElement(By.id("q_updated_at_lteq_datetime")).click();   //Update Date To
		driver.findElement(By.xpath("//a[contains(text(),'10')]")).click();
		
		driver.findElement(By.name("commit")).click();  //Submit-ClickOnFilterButton
		
		driver.findElement(By.name("clear_filters_btn")).click();       //clear filter
		
		driver.findElement(By.xpath("//a[contains(text(),'Updated At')]")).click(); //Update
		
		driver.findElement(By.xpath("//a[@class='delete_link member_link']")).click();  //Delete
			
			driver.quit();
	}

}
