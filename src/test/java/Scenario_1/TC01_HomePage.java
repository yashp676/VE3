package Scenario_1;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC01_HomePage {

    private WebDriver driver;
    private static ExtentReports extentReports;

    @BeforeSuite
    public void initializeExtentReports() {
    	
        ExtentSparkReporter sparkReport = new ExtentSparkReporter("C:\\Users\\yashp\\eclipse-workspace\\VE3\\Reports\\HomePage.html");
        extentReports = new ExtentReports();
        sparkReport.config().setTheme(Theme.DARK);
        sparkReport.config().setDocumentTitle("My Report");
        extentReports.attachReporter(sparkReport);
    }
    
        
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    

    @Test
    public void verifyHomePage() {
        ExtentTest test = extentReports.createTest("Verify HomePage")
            .assignAuthor("Yash")
            .assignCategory("Functional")
            .assignDevice("Windows");

        driver.get("https://www.ve3.global/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        test.info("Captured Page Title: " + actualTitle);

        if (actualTitle.equals("VE3 - Digital Solutions for the Future")) {
            test.pass("Page Title is verified. Title Captured: " + actualTitle);
        } else {
            test.fail("Page Title verification failed. Title Captured: " + actualTitle);
        }
    }

         @AfterMethod
         public void Screenshoot() throws IOException {
        	 TakesScreenshot ts=(TakesScreenshot) driver;
        	 File ramloc=ts.getScreenshotAs(OutputType.FILE);
        	 File destloc=new File("./Screenshots/VE3.png");
        	 FileUtils.copyFile(ramloc, destloc);         
        	 }
         
    
         @AfterTest
         public void tearDown() {
             if (driver != null) {
                 driver.quit();
             }
         }
         
    @AfterSuite
    public void generateReport() throws IOException {
        extentReports.flush();
        Desktop.getDesktop().browse(new File("C:\\Users\\yashp\\eclipse-workspace\\VE3\\Reports\\HomePage.html").toURI());
    }
    

   
}

