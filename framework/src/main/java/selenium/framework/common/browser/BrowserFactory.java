package selenium.framework.common.browser;

import org.openqa.selenium.WebDriver;
import selenium.framework.common.exceptions.SeleniumFrameworkException;
import selenium.framework.common.tests.TestParameters;

public class BrowserFactory {
    public WebDriver initializeDriver(final TestParameters parameters) throws SeleniumFrameworkException {
        switch (parameters.getBrowser()){
            case CHROME:
                return new BrowserConfig().getChromeDriver();
            case FIREFOX:
                return new BrowserConfig().getFirefoxDriver();
            default:
                throw new SeleniumFrameworkException("Incorrect Driver type");
        }
    }
}
