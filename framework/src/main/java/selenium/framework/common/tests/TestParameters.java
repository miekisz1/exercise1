package selenium.framework.common.tests;

import selenium.framework.common.browser.BrowserType;

public class TestParameters {
    private BrowserType browser;
    private String browserVersion;

    public TestParameters(BrowserType browserType, String browserVersion){
        this.browser = browserType;
        this.browserVersion=browserVersion;
    }

    public TestParameters(BrowserType browserType){
        this.browser = browserType;
        this.browserVersion=null;
    }

    public BrowserType getBrowser() {
        return browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }
}
