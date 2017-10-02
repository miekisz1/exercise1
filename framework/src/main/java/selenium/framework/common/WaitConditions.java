package selenium.framework.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.framework.common.others.Log;

import java.util.List;
import java.util.function.Function;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class WaitConditions {

    public static Function<WebDriver, ?> javaScriptValue(String script, String value){
        Log.message(String.format(" - Waiting for script"));

        Function<WebDriver, Boolean> conditions = d ->{
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)d;
            if(null == javascriptExecutor)
                return false;
            Object scriptOut = javascriptExecutor.executeScript(script);
            return null != scriptOut && scriptOut.toString().toLowerCase().trim().equals(value);
        };

        return conditions;
    }

    public static Function<WebDriver, WebElement> elementExists(By criteria){
        Log.message(String.format(" -  Waiting for element: '%s'", criteria));
        return d ->{
            List<WebElement> elementList = d.findElements(criteria);
            return elementList.isEmpty() ? null : elementList.get(0);
        };
    }
    public static Function<WebDriver, ?> elementNotExists(By criteria){
        Log.message(String.format(" -  Waiting until element: '%s' dissapear", String.valueOf(criteria)));
        return d -> d.findElements(criteria).isEmpty();
    }

}
