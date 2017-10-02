package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.framework.common.Base;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class CheckBox extends Base {
    public void selectCheckBox(boolean state, By selector){
        WebElement element = driver.findElement(selector);
        if(element.isSelected()!= state)
            element.click();
    }
}
