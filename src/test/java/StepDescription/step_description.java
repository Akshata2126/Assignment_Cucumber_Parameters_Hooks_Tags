package StepDescription;


import cucumber.api.java.en.When;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class step_description {
	
	public WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	 ExtentReports extent;
	 ExtentTest logger;

	
	 @Before
	    public void beforeScenario(){
		 
	
		 htmlReporter = new ExtentHtmlReporter(Reportpath());
			extent= new ExtentReports();
			extent.attachReporter(htmlReporter);			
			logger= extent.createTest("step_description");
	        System.out.println("This will run before the Scenario");
	    } 
	 public String Reportpath()
	 {
		 //Will give report path for different scenarios
		 String dest ="./Report/CucumberReport"+System.currentTimeMillis()+".html";
		 return dest;
	 }
	
	@Given("^Go to the google URL$")
	public void go_to_the_google_URL() throws Throwable {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Manoj\\Desktop\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
		
		driver.get("https://www.google.com");
		logger.log(Status.INFO, "Google browser is opened");
		
		logger.addScreenCaptureFromPath(captureScreen());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^user enter text to search \"([^\"]*)\" in search text box$")
	public void user_enter_text_to_search_in_search_text_box(String arg1) throws Throwable {
		driver.findElement(By.name("q")).sendKeys(arg1);
		driver.findElement(By.name("q")).submit();
	
		logger.log(Status.INFO, "Click on Search Textbox and search for the site");
		logger.addScreenCaptureFromPath(captureScreen());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^click on first link$")
	public void click_on_first_link() throws Throwable {
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[1]/a/h3")).click();	   
		logger.log(Status.INFO, "Click on the first link");
		logger.addScreenCaptureFromPath(captureScreen());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Then("^Clicked link HomePage is displayed take screenshot and attach to Extent report$")
	public void clicked_link_HomePage_is_displayed_take_screenshot_and_attach_to_Extent_report() throws Throwable {
		String title=driver.getTitle();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info(title);
		
		
	}	     
	
	 @After
	    public void afterScenario(){
		 	extent.flush();
		 	driver.close();
	        System.out.println("This will run after the Scenario");
	    }

public String captureScreen() throws IOException {
	TakesScreenshot screen = (TakesScreenshot) driver;
	File src = screen.getScreenshotAs(OutputType.FILE);
	
	String dest ="/ExtentReport/Report/Screenshot/"+System.currentTimeMillis()+".png";
	File target = new File(dest);
	FileUtils.copyFile(src, target);
	return dest;
}	  
}