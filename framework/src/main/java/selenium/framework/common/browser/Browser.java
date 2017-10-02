package selenium.framework.common.browser;

import selenium.framework.common.PageCommon;
import selenium.framework.common.exceptions.SeleniumFrameworkException;
import selenium.framework.common.others.Log;
import selenium.framework.common.tests.TestParameters;

public class Browser extends PageCommon {
    public Browser(final TestParameters parameters) throws SeleniumFrameworkException {
        Log.message("Open browser: %s", parameters.getBrowser());
        driver = new BrowserFactory().initializeDriver(parameters);
    }
}
