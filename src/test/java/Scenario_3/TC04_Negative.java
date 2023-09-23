package Scenario_3;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class TC04_Negative {

	private WebDriver driver;
    private static ExtentReports extentReports;
    ExtentTest test;
    
    
    @BeforeSuite
    public void initializeExtentReports() {
        ExtentSparkReporter sparkReport = new ExtentSparkReporter("ContactN.html");
        extentReports = new ExtentReports();
        sparkReport.config().setTheme(Theme.DARK);
        sparkReport.config().setDocumentTitle("ContactFormN");
        extentReports.attachReporter(sparkReport);
    }
    
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        test = extentReports.createTest("Verify Contact Form")
                .assignAuthor("Yash")
                .assignCategory("Functional")
                .assignDevice("Windows");
    }

    @Test
    public void formfilling() throws EncryptedDocumentException, IOException {
    	FileInputStream fis = new FileInputStream("./Book1.xlsx");
    	Workbook ws = WorkbookFactory.create(fis);
    	String url = ws.getSheet("Sheet1").getRow(2).getCell(0).getStringCellValue();
    	String your_name = ws.getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue();
    	String your_email = ws.getSheet("Sheet1").getRow(2).getCell(2).getStringCellValue();
             	
    	driver.get(url);
    	 WebDriverWait wait = new WebDriverWait(driver, 30);
    	driver.findElement(By.xpath("//button[@class='cmplz-btn cmplz-accept']")).click();
    	driver.findElement(By.name("your-name")).sendKeys(your_name);
    	driver.findElement(By.name("your-email")).sendKeys(your_email);

    	driver.findElement(By.xpath("//*[@id=\"wpcf7-f35890-p39497-o1\"]/form/p[3]/input")).click();
    	
    	WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Please enter an email address.']")));

        String expectedMessage = "Please enter an email address.";
        test.info("Captured Message: " + expectedMessage);
        String actualMessage = confirmationMessage.getText();
        if (actualMessage.contains(expectedMessage)) {
        	
        	test.pass("Error Message Displayed Successfully: ");
        } else {
        	test.pass("Error Message Displayed Failed:");
        }

    }
    @AfterMethod
    public void Screenshoot() throws IOException {
   	 TakesScreenshot ts=(TakesScreenshot) driver;
   	 File ramloc=ts.getScreenshotAs(OutputType.FILE);
   	 File destloc=new File("./Screenshots/VE3N.png");
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
            Desktop.getDesktop().browse(new File("contactN.html").toURI());
        }
        


}
