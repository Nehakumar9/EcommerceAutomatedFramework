package TestBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) throws IOException {
        //Load config
         FileReader fileReader = new FileReader(".//src//test//resources//config.properties");
         p = new Properties();
         p.load(fileReader);
        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()){
            case "chrome":driver = new ChromeDriver();break;
            case "edge": driver= new EdgeDriver();break;
            default:System.out.println("Invalid browser");return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();

    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.close();

    }
    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumeric(){
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString+"@"+generatedNumber);
    }

    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // 1. Keep using the absolute path to physically save the file on your machine
        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "-" + timeStamp + ".png";
        File destFile = new File(targetFilePath);
        sourcefile.renameTo(destFile);

        // 2. RETURN a relative path that tells the HTML report (in the /reports/ folder)
        // to step out (../) and look inside the /screenshots/ folder.
        return "../screenshots/" + tname + "-" + timeStamp + ".png";
    }
}
