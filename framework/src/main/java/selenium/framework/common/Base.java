package selenium.framework.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.framework.common.driver.IDriver;
import selenium.framework.common.others.Log;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public abstract class Base implements IDriver{
    protected WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public <T> T wait(final Function<WebDriver, T> expectedConditions, int timeInSeconds){
        Log.message("  - - Waiting max by: '%s' seconds", timeInSeconds);

        setImplicityWait(0);
        T object = new WebDriverWait(driver, timeInSeconds)
                .pollingEvery(0, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(expectedConditions);
        setImplicityWait(SeleniumConfig.defaultImplicityWait);

        Log.message("  - - - Wait finished");

        return object;
    }

    protected void setImplicityWait(final int timeInSeconds){
        driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }
}
