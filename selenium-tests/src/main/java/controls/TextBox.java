package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.framework.common.Base;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class TextBox extends Base {
    public void setTextBox(String text, By selector){
        WebElement element = driver.findElement(selector);
        element.clear();
        new Actions(driver).sendKeys(text).build().perform();
    }
}
