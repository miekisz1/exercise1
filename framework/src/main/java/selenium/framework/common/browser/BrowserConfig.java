package selenium.framework.common.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import selenium.framework.common.driver.WebDriverEventHandler;
import selenium.framework.common.exceptions.SeleniumFrameworkException;

import java.util.concurrent.TimeUnit;

public class BrowserConfig {
    private WebDriver driver;

    public WebDriver getChromeDriver(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        driver = new ChromeDriver(capabilities);

        confiDriver();
        EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
        eDriver.register(new WebDriverEventHandler());

        return eDriver;
    }

    public WebDriver getFirefoxDriver() throws SeleniumFrameworkException {
        throw new SeleniumFrameworkException("Firefox not implemented");
    }

    private void confiDriver()
    {
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "0");
    }
}