package webelements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;
	Faker faker =new Faker();
	List<WebElement> inputBoxes;
	List<WebElement> dropDowns ;
	List<WebElement> checkboxes ;
	List<WebElement> radioButtons;
	List<WebElement> buttons; 
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test
	public void myLinks() {
		driver.get("https://github.com/");
		//List<WebElement> links = driver.findElements(By.tagName("a"));
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		
		//how many links on github home page
		int numberOfLinksOnGithub = links.size();
		System.out.println("number of links: " + numberOfLinksOnGithub);
		//print test of each link
		for(WebElement link:links) {
			if(!link.getText().isEmpty()) {
			System.out.println(link.getText());
			}
		}
		//add each link text into a list of strings
		
		List<String> linkNames = new ArrayList<>();
		
		for(WebElement link : links) {
			if(!link.getText().isEmpty()) {
				linkNames.add(link.getText());
			}
	
			
	}
		
		}
	/*** navigate to https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg 
	 * Find all input boxes and assign to List of webelements -> 2
     * Find all drop down boxes and assign to another List of webelements -> 3
     * Find all check boxes and assign to another List of webelements -> 9
     * Find all radio boxes and assign to another List of webelements -> 9
     * Find all buttons and assign to another List of webelements -> 1
     * assert each one’s count
     */
	
	@Test
	public void SeleniumWebElementsForm() {
		driver.get("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg ");
		inputBoxes =driver.findElements(By.xpath("//input[@type='text']"));
		dropDowns =driver.findElements(By.tagName("select"));
		checkboxes =driver.findElements(By.xpath("//input[@type='checkbox']"));
		radioButtons =driver.findElements(By.xpath("//input[@type='radio']"));
		buttons =driver.findElements(By.tagName("button"));
		
		assertEquals(inputBoxes.size(), 2);
		assertEquals(dropDowns.size(), 3);
		assertEquals(checkboxes.size(), 9);
		assertEquals(radioButtons.size(), 9, "message will show if it fails");
		assertEquals(buttons.size(), 1, "message will show if it fails");
	}
	/* Homework:
     *     Loop through each inputbox and enter random names
     *  Loop through each dropDown and randomly select by index
     *  Loop through each checkBoxes and select each one
     *  Loop through each radioButton and click one by one by waiting one second intervals
     *  click all buttons
     */
	@Test
	public void loops1() {
		for(int i=0;i<inputBoxes.size();i++) {
			String input = faker.name().firstName();
			inputBoxes.get(i).sendKeys(input);
			
		}
			
		}
	@Test
	public void loops2() {
		for(int i=0;i<dropDowns.size();i++) {
			int drop = faker.number().numberBetween(0, 4);
			Select select = new Select(dropDowns.get(i));
			select.selectByIndex(drop);
		
	}
	}
	@Test
	public void loops3() {
		for(int i=0;i<checkboxes.size();i++) {
			checkboxes.get(i).click();
		}
	}
	@Test
	public void loops4() throws InterruptedException {
		for(int i=0;i<radioButtons.size();i++) {
			radioButtons.get(i).click();
			Thread.sleep(1000);
		}
	}
	
	
//	
//	@Test
//	public void slideShow() throws InterruptedException {
//		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
//		List<WebElement> images = driver.findElements(By.tagName("img"));
//		List<String> srcs = new ArrayList<>();
//		
//		for(WebElement flower: images) {
//			srcs.add(flower.getAttribute("src"));
//		}
//		
//		for (String link : srcs) {
//			driver.get(link);
//			Thread.sleep(1234);
//		}
//		
//	}
	@AfterClass
	public void cleanUp() {
		driver.close();
	}
}