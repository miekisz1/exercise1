package selenium.framework.common;

import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.framework.common.driver.IDriver;
import selenium.framework.common.others.Ioc;
import selenium.framework.common.others.Log;

public class PageCommon extends Base {

    public <T extends PageCommon> T openUrl(String url, Class<T> clazz) {
        driver.navigate().to(url);

        Log.message("Application is opened, Address: '%s' is set", url);

        wait(ExpectedConditions.urlContains("github"), 60);
        wait(WaitConditions.javaScriptValue("return (document.readyState == 'complete')", "true"), 120);

        setImplicityWait(SeleniumConfig.defaultImplicityWait);

        return pageClassFactory(clazz);
    }

    public void closeBrowser(){
        driver.quit();
        Log.message("Browser is closed");
    }

    public <T extends IDriver> T pageClassFactory(Class<T> clazz){
        T object = Ioc.getInstance(clazz);
        if(null == object.getDriver())
            object.setDriver(driver);
        return object;
    }
}
