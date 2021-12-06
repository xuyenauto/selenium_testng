package testcases;

import helpers.CommonActions;
import helpers.Constants;
import helpers.Log;
import helpers.ReadDataFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.File;


public class TestBase {

    protected static WebDriver driver;
    ReadDataFile readDataFile = new ReadDataFile();
    String browser = readDataFile.readJsonFile("/config.json","Constant","browser");
    String url = readDataFile.readJsonFile("/config.json","Constant","url");
    String firefoxPath = readDataFile.readJsonFile("/config.json","Constant","firefox_install_location");

    @BeforeTest
    public WebDriver initializeDriver() {
        switch (browser){
            case "firefox":
                if(firefoxPath == null || firefoxPath.equals("")){
                    Log.error("You are missing the firefox installation location");
                    return null;
                }
                File pathBinary = new File(firefoxPath);
                FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
                DesiredCapabilities desired = new DesiredCapabilities();
                FirefoxOptions options = new FirefoxOptions();
                desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(options);
                break;
            case "edge":
                //todo
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        openHomePage();
        return driver;
    }



//    /**
//     * Embed the screenshot when ending the test even the result is passed or failed
//     */
//    @AfterTest
//    public void embedScreenshot() throws IOException {
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String currentDir = System.getProperty("user.dir");
//        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
//        if (driver != null) {
//            driver.manage().deleteAllCookies();
//            driver.close();
//            driver.quit();
//        }
//    }

    @AfterSuite
    public void tearDown(){
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
//        }
        }
    }

    private void openHomePage() {
        driver.navigate().to(String.valueOf(url));
        if(CommonActions.getCurrentOperation().contains("Mac")){
            driver.manage().window().fullscreen();
        }else{
            driver.manage().window().maximize();
        }
    }

}
