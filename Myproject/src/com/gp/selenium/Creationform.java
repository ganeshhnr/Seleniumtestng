package com.gp.selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Creationform {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		
		driver=new ChromeDriver();
		
		driver.get("http://talentify.in:8999/content/");
		
		driver.manage().window().maximize();
		
		System.out.println("enter into page");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   }
		
	@Test
	public void loginApp() throws InterruptedException{
		try {
			WebElement user=driver.findElement(By.name("email"));
			
			WebElement password=driver.findElement(By.name("password"));
			
			WebElement button=driver.findElement(By.xpath("//button[@type='submit']"));
			button.click();
			
		} catch (Exception e) {
			
			System.out.println("error found "+e.getMessage());
		}
		 System.out.println("logged in to page");
	
		try {
			WebElement dropdown=driver.findElement(By.linkText("Course Administration"));
			dropdown.click();
			
			WebElement list=driver.findElement(By.linkText("Lesson(s)"));
			list.click();
			
		} catch (Exception e) {
			
			System.out.println("error msg found "+e.getMessage());
		}
		System.out.println("entered to next page");
		
		try {
			WebElement newlesson=driver.findElement(By.id("create_lessonzz"));
			newlesson.click();
			
			String parent=driver.getWindowHandle();
			
			Set<String> windows=driver.getWindowHandles();
			
			int count=windows.size();
			
			for (String child: windows) {
				
				if(!parent.equalsIgnoreCase(child)){
					
					driver.switchTo().window(child);
					
					//WebElement pagelesson=driver.findElement(By.xpath("//div[@class='form-group']/h4[text()='Lesson Name']"));
					
					WebElement lesson=driver.findElement(By.xpath("//input[@id='lesson_name_idd']"));
					lesson.sendKeys("first lesson");
					
					WebElement desc=driver.findElement(By.xpath("//textarea[@id='lesson_desc_idd']"));
					desc.sendKeys("This is the first lesson");
					
					WebElement duration=driver.findElement(By.xpath("//input[@id='lesson_duration_idd']"));
					duration.sendKeys("10");
					
					WebElement next=driver.findElement(By.linkText("Next"));
					next.click();
					Thread.sleep(3000);
					
					WebElement next1=driver.findElement(By.linkText("Next"));
					next1.click();
					Thread.sleep(3000);
					
					WebElement next2=driver.findElement(By.linkText("Next"));
					next2.click();
					Thread.sleep(3000);
					
					WebElement next3=driver.findElement(By.linkText("Finish"));
					next3.click();
					Thread.sleep(3000);
					
					System.out.println("successfully created");
					
					driver.close();
					}
				
				driver.switchTo().window(parent);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("error found "+e.getMessage());
		
		 }
		
		WebElement dashboard=driver.findElement(By.linkText("Dashboard"));
		dashboard.click();
		Thread.sleep(2000);
		
		WebElement dash1=driver.findElement(By.xpath("//div[@class='ibox']/div[@class='ibox-content product-box']//a[text()='first lesson']"));
		String expected1="first lesson";
		String actual=dash1.getText();
		
		Assert.assertEquals(actual, expected1);
		
		System.out.println("successfully it is done");
		
		
		
		driver.quit();
			
		
		}
}


