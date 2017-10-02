package page.objects.repository;

import org.openqa.selenium.By;
import selenium.framework.common.PageCommon;
import selenium.framework.common.WaitConditions;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class RepositoryCodePage extends PageCommon {
    public NewFilePage clickCreateNewFileButton(){
        driver.findElement(By.xpath("//button[@type='submit'][contains(text(), 'Create')]")).click();
        Log.message("'Create new file' button is clicked");

        wait(WaitConditions.elementExists(By.id("submit-file")), 30);

        return pageClassFactory(NewFilePage.class);
    }
}
