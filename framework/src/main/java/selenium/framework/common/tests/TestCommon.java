package selenium.framework.common.tests;

import org.testng.annotations.DataProvider;
import selenium.framework.common.browser.BrowserType;
import selenium.framework.common.others.Ioc;

public abstract class TestCommon {

    @DataProvider(parallel = true, name = "testParameters")
    public Object[][] testParameters(){
        return new Object[][]{
                {new TestParameters(BrowserType.CHROME)}
        };
    }

}
