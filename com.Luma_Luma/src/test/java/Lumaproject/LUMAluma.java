package Lumaproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LUMAluma {
	WebDriver driver;

	@DataProvider(name = "loginData")
	public static Object[][] provideLoginData() {
	    return new Object[][] {
	            {"Sahil.sk2114@gmail.com", "un20@@sh1707"},          
	    };
	}

	@BeforeSuite
	public void launch() throws InterruptedException {
    driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://magento.softwaretestingboard.com/");
	Thread.sleep(2000);
	System.out.println("url launched...");
	
	System.out.println("application URL is: " + driver.getCurrentUrl());	// validaiton command
	System.out.println("application title is: " + driver.getTitle());
	
	System.out.println("home page load");
	}
	
	@Test(dataProvider = "loginData")
	public void loginTest(String Username, String password) {
	driver.findElement(By.xpath("//div[@class='panel wrapper']/div/ul/li[2]")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Username);
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	driver.findElement(By.xpath("//button[@id='send2']")).click();
	System.out.println("user login succesfully...");
	}
	
	@AfterMethod
	public void elements() throws InterruptedException {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[@id='ui-id-4']")).click();// women 
		driver.findElement(By.xpath("//ol[@class='items']/li/a")).click();//women tops
		driver.findElement(By.xpath("//main[@id='maincontent']/div[3]/div[1]/div[3]/ol/li[2]/div/a/span/span/img")).click();// selected product
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-169']")).click();
		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-57']")).click();
		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
		System.out.println("user successfully add a product to the cart");
		Thread.sleep(2000);
		driver .findElement(By.xpath("//a[@class='logo']")).click();
		System.out.println("webite logo checked..");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='secondary']/a")).click();//cart delete button
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[4]/aside[2]/div[2]/footer/button[2]")).click();//pop up comfirmation'
		Thread.sleep(1000);
		System.out.println("pop up clicked...");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");		
		System.out.println("Application page is srollable..");

		driver.findElement(By.xpath("//main[@id='maincontent']/div[3]/div/div[2]/div[3]/div/div/ol/li[1]/div/a/span/span/img")).click();
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-169']")).click();
		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-57']")).click();
		driver.findElement(By.xpath("//input[@name='qty']")).clear();
		driver.findElement(By.xpath("//input[@name='qty']")).sendKeys("2");
		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("womens top");
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		System.out.println("search button is clickable...");
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		System.out.println("user able to scroll the page ..");
	}
	@AfterClass
	public void errordisplayed() throws InterruptedException {
		
		driver .findElement(By.xpath("//a[@class='logo']")).click();	

		driver.findElement(By.xpath("//main[@id='maincontent']/div[3]/div/div[2]/div[3]/div/div/ol/li[2]/div/a/span/span/img")).click();
		driver.findElement(By.xpath("//div[@class='product-social-links']/div/a[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@data-role='compare-products-link']/a")).click();// praduct compare link
		
		driver.findElement(By.xpath("//table[@id='product-comparison']/tbody[1]/tr/td/a/span/span/img")).click();
		//driver.findElement(By.xpath("//img[@class='fotorama__img']")).click();
		Thread.sleep(1000);
		driver.navigate().back();//back to
		driver.findElement(By.xpath("//table[@id='product-comparison']/thead/tr/td/a")).click();//remove compare product
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/aside[2]/div[2]/footer/button[2]")).click();//remove pop up
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='search']")).clear();
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("//");// 7 test case
		driver.findElement(By.xpath("//button[@title='Search']")).click();

		WebElement errorMessage = driver.findElement(By.cssSelector("div.message.notice"));
		String errorMessageText = errorMessage.getText();
		System.out.println("Error message: " + errorMessageText);
		System.out.println("the error message is shown correctly..");
		
	}
	@AfterTest
	public void logo() throws InterruptedException {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		
		driver .findElement(By.xpath("//a[@class='logo']")).click();	
		driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")).click();// collection cart button
		driver.findElement(By.xpath("//a[@class='action viewcart']")).click();//9cart
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		
		driver.findElement(By.xpath("//a[@class='action action-delete']")).click();//9 deleting cart
		driver.findElement(By.xpath("//main[@id='maincontent']/div[3]/div/div[2]/p[2]/a")).click();//click to continue shopping
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		driver.findElement(By.xpath("//img[@alt='Fusion Backpack']")).click();//10 new product
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
 
		driver.findElement(By.linkText("Add Your Review")).click();//11 review
		driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("I've had this thing for a really long time and it barely shows any signs of wear and tear");
		driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("It's really big, too! I've taken it on day trips as well as short vacations and usually have no trouble finding room for my stuff.");
		
		WebElement rating = driver.findElement(By.xpath("//label[@id='Rating_3_label']"));
		System.out.println("review button is clickable...");
		Actions act = new Actions(driver);
		act.click(rating).build().perform();
		driver.findElement(By.xpath("//button[@class='action submit primary']")).click();
		System.out.println("The user add review succesfully..");

		driver.findElement(By.xpath("//a[@class='action towishlist']")).click();//wishlist
		System.out.println("user able to checked the wishlist..");
		//driver.findElement(By.xpath("//div[@class='primary']/a")).click();	
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();//account drop down	
		driver.findElement(By.xpath("//div[@class='customer-menu']/ul/li/a")).click();//my account
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		driver.findElement(By.xpath("//ul[@class='nav items']/li[2]/a")).click();//my order
		driver.findElement(By.xpath("//ul[@class='nav items']/li[3]/a")).click();//my download product
		driver.findElement(By.xpath("//ul[@class='nav items']/li[4]/a")).click();//whishlist
		driver.findElement(By.xpath("//ul[@class='nav items']/li[6]/a")).click();//my address book
		driver.findElement(By.xpath("//button[@role='add-address']")).click();
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys("tata birla");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("5849787945");
		driver.findElement(By.xpath("//input[@id='street_1']")).sendKeys("32 London Bridge St");
		driver.findElement(By.xpath("//input[@id='street_2']")).sendKeys("London SE1 9SG");
		driver.findElement(By.xpath("//input[@id='street_3']")).sendKeys("United Kingdom");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("The Shard");
		System.out.println("user able to add an address...");
		WebElement state = driver.findElement(By.xpath("//select[@id='region_id']"));
		Select s1 = new Select(state);
		s1.selectByVisibleText("Nevada");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("SE1 9SG");
		
		WebElement country = driver.findElement(By.xpath("//select[@name='country_id']"));
		Select s2 = new Select(country);
		s2.selectByVisibleText("United Kingdom");
		Thread.sleep(1000);		
		
		driver.findElement(By.xpath("//form[@id='form-validate']/div/div[1]/button")).click();
		
		driver.findElement(By.xpath("//ul[@class='nav items']/li[8]/a")).click();//my stored payment
		driver.findElement(By.xpath("//ul[@class='nav items']/li[10]/a")).click();//my product review
		driver.findElement(By.xpath("//ul[@class='nav items']/li[7]/a")).click();//account information
		driver.findElement(By.xpath("//div[@class='actions-toolbar']/div/button")).click();//account info save button
				
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		driver.findElement(By.xpath("//main[@id='maincontent']/div[2]/div[3]/div[3]/div[2]/div")).click();
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();//account drop down	
		driver.findElement(By.xpath("//div[@class='customer-menu']/ul/li[2]")).click();//whishlist dropdown button
		driver.findElement(By.xpath("//a[@id='ui-id-5']")).click();//mens collection
		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a")).click();//mens jacket collection 
		driver.findElement(By.xpath("//div[@id='narrow-by-list']/div[4]/div[1]")).click();//colour drop down 
		driver.findElement(By.xpath("//a[@aria-label='Red']/div")).click();//red colour selection
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		
		driver.findElement(By.xpath("//a[@id='ui-id-6']")).click();//gear selection..
		System.out.println("user able to click gear button...");
		
		driver.findElement(By.xpath("//dl[@id='narrow-by-list2']/dd/ol/li[1]/a")).click();//bags selection..
		System.out.println("user able to select bags collection ");
		
		driver.findElement(By.xpath("//a[@id='ui-id-7']")).click();//training selection 
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		//mens deals..
		driver.findElement(By.xpath("//dl[@id='narrow-by-list2']/dd/ol/li")).click();//video download button..
		driver.findElement(By.xpath("//a[@id='ui-id-8']")).click();//sales selection 
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		System.out.println("user able to click training selection and able to scroll the page..");
	
		driver.findElement(By.xpath("//div[@class='categories-menu']/ul/li/a")).click();//hoodies and sweartshirt
		driver.navigate().back();//back to

		driver.findElement(By.xpath("//div[@class='categories-menu']/ul/li[2]/a")).click();//jackets button
		driver.navigate().back();//back to

		driver.findElement(By.xpath("//div[@class='categories-menu']/ul/li[3]/a")).click();//tees button
		driver.navigate().back();//back to

		driver.findElement(By.xpath("//div[@class='categories-menu']/ul/li[4]/a")).click();//pants button
		driver.navigate().back();//back to
		
		driver.findElement(By.xpath("//div[@class='categories-menu']/ul/li[5]/a")).click();//shorts button
		driver.navigate().back();//back to
		System.out.println("user able to check deals of the mens product");

		//women deals 
		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div/div/ul[2]/li[1]/a")).click();//hoodies and sweatshirt
		driver.navigate().back();//back to

		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div/div/ul[2]/li[2]/a")).click();
		driver.navigate().back();//back to

		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div/div/ul[2]/li[3]/a")).click();
		driver.navigate().back();//back to
		
		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div/div/ul[2]/li[4]/a")).click();
		driver.navigate().back();//back to
		
		driver.findElement(By.xpath("//main[@id='maincontent']/div[4]/div[2]/div/div/ul[2]/li[5]/a")).click();
		driver.navigate().back();//back to
		System.out.println("user able to check deals of the women product");

		driver.findElement(By.xpath("//a[@id='ui-id-3']")).click();
		System.out.println("User able to what's new button..");
		driver.findElement(By.xpath("//div[@class='breadcrumbs']/ul/li/a")).click();//home button
		System.out.println("user able to click home page");
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();//account drop down	
		System.out.println("user able to click account drop down list ..");
		driver.findElement(By.xpath("//li[@class='authorization-link']/a")).click();//signout button
		System.out.println("user able to signout successfully..");
	}
	@AfterSuite
	public void registration() {
		driver.findElement(By.xpath("//div[@class='panel wrapper']/div/ul/li[2]")).click();
		driver.findElement(By.xpath("//main[@id='maincontent']/div[3]/div/div[2]/div[2]/div[2]/div/div/a")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("HRitt");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("sighania");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Ritisigni@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Sighaia$@1234");
		driver.findElement(By.xpath("//input[@name='password_confirmation']")).sendKeys("Sighaia$@1234");
		driver.findElement(By.xpath("//div[@class='actions-toolbar']/div/button")).click();
		System.out.println("user can add a new customer..");
		driver.quit();
	}
}

