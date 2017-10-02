package selenium.framework.common;

import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class WaitFor {
    public static Function<WebDriver, ?> PageReady(){
        return WaitConditions.javaScriptValue("return (document.readyState == 'complete')","true");

    }
}
