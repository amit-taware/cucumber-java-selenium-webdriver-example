package masterhooks;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Setup {

    public static WebDriver driver;

    @Before
    public void setWebDriver() throws Exception {

        String host = "localhost";

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
    }
}
