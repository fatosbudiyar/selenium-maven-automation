package webelements;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {
	WebDriver driver;
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test
	public void WebElementExamples() {
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");	
		WebElement email = driver.findElement(By.name("Email"));
		String value = email.getAttribute("value");
		String maxLenght = email.getAttribute("maxlenght");
		String type = email.getAttribute("type");
		String tag= email.getTagName();
		boolean b= email.isEnabled();
		System.out.println("value : "+value+"\n"+"maxlenght: "+ maxLenght+"\n"+
		"type :"+type+"\n"+"tag: "+tag+ "\n"+"enabled : "+b);
		
		assertEquals(value, "youremail@mail.com");
		email.clear();
		email.sendKeys("anotheremail@mail.com");
		
		WebElement country = driver.findElement(By.id("Address_Country"));
		Select selectCountry = new Select(country);
		
		String d =(selectCountry.getFirstSelectedOption().getText());
		System.out.println(d);
		selectCountry.selectByIndex(67);
		
		//lets to generate StaleElementException
		WebElement cSalary = driver.findElement(By.name("Number"));
		assertEquals(cSalary.isDisplayed(), true);
		
		driver.get("http://google.com");
		//driver.findElement(By.xpath("//em[.=' Next ]")).click();
		cSalary.sendKeys("123456");//staleElementReferenceException --> it can not find the xpath because it is in different page
		
		
	}

}
