package selenium.framework.common.driver;

import org.openqa.selenium.WebDriver;

public interface IDriver {
    void setDriver(WebDriver driver);
    WebDriver getDriver();
}
