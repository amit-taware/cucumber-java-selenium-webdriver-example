package masterhooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {

    public static WebDriver driver;
    public static RequestSpecification request;
    public static String Token = "c1688fb57e5c49a62095180fe79b176aa90a70b8";

    @Before
    public void setWebDriver(Scenario scenario) throws Exception {

        String host = "localhost";

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }
        String browser = System.getProperty("BROWSER");
        DesiredCapabilities dc = new DesiredCapabilities();
        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("Name",scenario.getName());

        if (browser == null) {
            browser = "local";
        }
        switch (browser) {
            case "local":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("['start-maximized']");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                dc.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(completeUrl), dc);
                break;
            case "chrome":
                dc.setBrowserName("chrome");
                driver = new RemoteWebDriver(new URL(completeUrl), dc);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
}
