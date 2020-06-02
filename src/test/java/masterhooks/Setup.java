package masterhooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
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

    @Before
    public void setWebDriver(Scenario scenario) throws Exception {

        String host = "hub";

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }
        String browser = System.getProperty("BROWSER");
        DesiredCapabilities dc;
        String completeUrl = "http://" + host + ":4444/wd/hub";

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
                dc = DesiredCapabilities.firefox();
                driver = new RemoteWebDriver(new URL(completeUrl), dc);
                break;
            case "chrome":
                dc = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL(completeUrl), dc);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
}
