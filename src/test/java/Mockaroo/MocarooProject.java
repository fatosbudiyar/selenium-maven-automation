package Mockaroo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MocarooProject {

	WebDriver driver;
	List<String>cities ;
	List<String> countries;
	//2. Navigate to https://mockaroo.com/
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("https://mockaroo.com/");
	}

	//3. Assert title is correct.
	@Test
	public void testA() {
		String title = driver.getTitle();
		System.out.println(title);
		assertEquals(title, "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel");
	}
//4. Assert Mockaroo and realistic data generator are displayed
	@Test
	public void testB() {
		String actual = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String expected = "mockaroo";
		System.out.println("Actual:" + actual + " Expected:" + expected);
		assertEquals(actual, expected);
		actual = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		expected = "realistic data generator";
		System.out.println("Actual:" + actual + " Expected:" + expected);
		assertEquals(actual, expected);
	}
	
//5. Remote all existing fields by clicking on x icon link

//	@Test
//	public void remove() {
//		for(int i =6; i>=1;i--) {
//			driver.findElement(By.xpath("(//a[@class='close remove-field remove_nested_fields'])["+i+"]")).click();
//			}
//	}
	@Test
	public void testC() throws InterruptedException {
		List<WebElement> xButtons = driver
				.findElements(By.xpath("//div[@id='fields']//a[@class='close remove-field remove_nested_fields']"));
		// click on all x buttons to clear fields
		for (WebElement each : xButtons) {
			each.click();
		}
	}
	
	//6. Assert that ‘Field Name’ , ‘Type’, ‘Options’ labels are displayed
	@Test
	public void testD() {
		assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-name']")).getText(), "Field Name");
		assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-type']")).getText(), "Type");
		assertEquals(driver.findElement(By.xpath("//div[@class = 'column column-header column-options']")).getText(), "Options");
		

	}
//	7. Assert that ‘Add another field’ button is enabled. Find using xpath with tagname and text. isEnabled() method in selenium
	
	 @Test
	    public void testE() {
	        assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).isEnabled());
	        
	    }
		

//	8. Assert that default number of rows is 1000.
@Test
public void testF() {
	 assertEquals(driver.findElement(By.xpath("//input[@class='medium-number form-control']")).getAttribute("value"), "1000");
	
}

//	9. Assert that default format selection is CSV

@Test
public void testG() {
assertEquals(driver.findElement(By.xpath("//select[@id='schema_file_format']")).getAttribute("value"),"csv");
}


//	10. Assert that Line Ending is Unix(LF)

@Test
public void testH() {
assertEquals(driver.findElement(By.xpath("//select[@id='schema_line_ending']")).getAttribute("value"),"unix");
}
//	11. Assert that header checkbox is checked and BOM is unchecked
@Test
public void testI() {
	assertEquals(driver.findElement(By.xpath("//input[@name='schema[include_header]'][1]")).getAttribute("type"),"hidden");
	assertEquals(driver.findElement(By.xpath("//input[@name='schema[include_header]'][2]")).getAttribute("type"),"checkbox");
	
}


//12. Click on ‘Add another field’ and enter name “City”
@Test
public void testJ() {
	driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
	driver.findElement(By.xpath(
			"(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]"))
			.sendKeys("City");
	
	
}

//13. Click on Choose type and assert that Choose a Type dialog box is displayed.
@Test
public void testK() {
	driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
	
	assertTrue(driver.findElement(By.xpath("//body[@class='mockaroo modal-open']")).isDisplayed());
	
}

////14. Search for “city” and click on City on s}
@Test
public void testL() throws InterruptedException {
	driver.findElement(By.id("type_search_field")).sendKeys("city");
	driver.findElement(By.xpath("//*[text()='City']")).click();
	Thread.sleep(3000);
}

////15. Repeat steps 12-14 with field name and type “Country”
@Test
public void testM() throws InterruptedException {
	
	driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
	driver.findElement(By.xpath(
			"(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]"))
			.sendKeys("Country");
	
	
driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
	
	assertTrue(driver.findElement(By.xpath("//body[@class='mockaroo modal-open']")).isDisplayed());
	Thread.sleep(2000);
	
	driver.findElement(By.id("type_search_field")).clear();
	
	driver.findElement(By.id("type_search_field")).sendKeys("country");
	driver.findElement(By.xpath("//*[text()='Country']")).click();
	Thread.sleep(3000);
}

//16. Click on Download Data.
@Test
public void testN() {
	driver.findElement(By.id("download")).click();

}

@Test
public void testO() throws IOException {
			// Step 17. Open the downloaded file using BufferedReader
			FileReader fr = new FileReader("/Users/fatosbudiyar/Downloads/MOCK_DATA (3).csv");
			// FileReader fr = new FileReader("Cities_and_Countries.txt");
			BufferedReader br = new BufferedReader(fr);

			// Step 18. Assert that first row is matching with Field names that we selecte
			String line = br.readLine();
			assertEquals(line, "City,Country");

			// Step 19. Assert that there are 1000 records
			// Step 20. From file add all Cities to Cities ArrayList
			// Step 21. Add all countries to Countries ArrayList
			 cities = new ArrayList();
			 countries = new ArrayList();

			int count = 0;
			while ((line = br.readLine()) != null) {
				cities.add(line.substring(0, line.indexOf(",")));
				countries.add(line.substring(line.indexOf(",") + 1));

				count++;
			}
			// System.out.println(count);
			assertEquals(count, 1000);
			System.out.println(cities);
			System.out.println(countries);

			// Step 22. Sort all cities and find the city with the longest name and shortest
			// name
			Collections.sort(cities);
			int maxLength = cities.get(0).length();
			int maxIndex = 0;
			int minLength = cities.get(0).length();
			int minIndex = 0;
			for (int i = 1; i < cities.size(); i++) {
				if (cities.get(i).length() > maxLength) {
					maxLength = cities.get(i).length();
					maxIndex = i;
				}
			}
			System.out.println(
					cities.get(maxIndex) + " is the longest city name in cities list. It has " + maxLength + " letters");
			for (int i = 1; i < cities.size(); i++) {
				if (cities.get(i).length() < minLength) {
					minLength = cities.get(i).length();
					minIndex = i;
				}
			}
			System.out.println(
					cities.get(minIndex) + " is the shortest city name in cities list. It has " + minLength + " letters");

			// Step 23. In Countries ArrayList, find how many times each Country is
			// mentioned. and print out
			Collections.sort(countries);
			Set<String> uniqueCountries = new HashSet(countries);
			for (String each : uniqueCountries) {
				System.out.println(each + " - " + Collections.frequency(countries, each));
			}

			// Step 24. From file add all Cities to citiesSet HashSet
			Set<String> citiesSet = new HashSet<String>(cities);

			// Step 25. Count how many unique cities are in Cities ArrayList and assert that
			// it is matching with the count of citiesSet HashSet.
			List<Integer> cityCounts=new ArrayList();
			List<String> uniqueCities=new ArrayList();

			for (int i = 0; i < cities.size(); i++) {
				int cityCount = 1;
				for(int j=0; j<cities.size(); j++) {
					if (!cities.get(i).equals(cities.get(j)) && (!uniqueCities.contains(cities.get(j)))) {
						cityCount++;
						uniqueCities.add(cities.get(j));
					} else {
						continue;
					}
				}
			//cityCounts.add(cityCount);
			}
			assertEquals(uniqueCities.size(),citiesSet.size());

			// Step 26. Add all Countries to countrySet HashSet
			Set<String> countrySet = new HashSet(countries);
			
			
			// Step 27. Count how many unique countries are in Countries ArrayList and
			// assert that it is matching with the count of countrySet HashSet.
			List<String> countryCount=new ArrayList();

			for (int i = 0; i < countries.size(); i++) {
				for(int j=0; j<countries.size(); j++) {
					if (!countries.get(i).equals(countries.get(j)) && (!countryCount.contains(countries.get(j)))) {
						countryCount.add(countries.get(j));
					} else {
						continue;
					}
				}
			}
			assertEquals(countryCount.size(),countrySet.size());
			
			
			// Step 28. Push the code to any GitHub repo that you have and submit the url

		}

		@AfterClass
		public void cleanUp() {
			driver.close();
		}

	}
