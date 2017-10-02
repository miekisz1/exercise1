package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.framework.common.Base;

/**
 * Created by PLARMIE on 2017-10-02.
 */
public class ComboBox extends Base{

    public void selectItem(String itemText, By selector){
        WebElement element = driver.findElement(selector);
        new Actions(driver).click(element).sendKeys(itemText).sendKeys(Keys.ENTER).build().perform();
    }
}
