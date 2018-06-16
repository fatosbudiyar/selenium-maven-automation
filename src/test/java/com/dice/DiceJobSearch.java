package com.dice;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		//Set up chrome driver path
		WebDriverManager.chromedriver().setup();
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		//fullcreen
		driver.manage().window().fullscreen();
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*Step 1. Launch browser and navigate to https://dice.com 
			  Expected: dice home page should be displayed
		*/
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		}else {
			System.out.println("Step FAIL. Dice homepage did not load successfully");	
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}
		
		String keyword ="java developer";
	
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "21075";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
			countResult +" results in " + location);
		}else {
			System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
					countResult +" results in " + location);
		}
		driver.navigate().to("https://dice.com");
		
		List <String> keywords = new ArrayList<>();
		keywords.add("selenium");
		keywords.add("java");
		keywords.add("webdriver");
		keywords.add("selenium automation");
		keywords.add("SDET");
		keywords.add("software");
		keywords.add("software engeneering");
		keywords.add("developer");
		keywords.add("test analyst");
		keywords.add("web developer");
		keywords.add("tester");
		keywords.add("software developer");
		keywords.add("maven");
		keywords.add("web designer");
		keywords.add("software development engineer");
		keywords.add("lead sdet");
		keywords.add("software systems engineer");
		keywords.add("QA automation ");
		keywords.add("QA automation engineer");
		keywords.add("selenium SDET");
		
		for (int i = 0; i < keywords.size(); i++) {
			driver.navigate().to("https://dice.com");
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(keywords.get(i));
			
			String location1 = "21075";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location1);
			
			driver.findElement(By.id("findTechJobs")).click();
			
			String count1 = driver.findElement(By.id("posiCountId")).getText();
			System.out.println(count1);
			//ensure count is more than 0
			int countResult1 = Integer.parseInt(count.replace(",", ""));
			
			if(countResult1 > 0) {
				System.out.println( "Step PASS: Keyword : " + keywords.get(i) +" search returned " +
				countResult1 +" results in " + location1);
			}else {
				System.out.println( "Step FAIL: Keyword : " + keywords.get(i) +" search returned " +
						countResult1 +" results in " + location1);
			}
			
			
		}
		
		driver.close();
		
		System.out.println("TEST COMPLETED -" + LocalDateTime.now());
				
	}

	
	}


