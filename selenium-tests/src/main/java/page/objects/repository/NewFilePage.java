package page.objects.repository;

import controls.TextBox;
import org.openqa.selenium.By;
import selenium.framework.common.PageCommon;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class NewFilePage extends PageCommon {

    public NewFilePage setFileName(String fileName){
        pageClassFactory(TextBox.class).setTextBox(fileName, By.cssSelector("input[name='filename']"));
        Log.message("'File name' is set: '%s'", fileName);

        return  this;
    }

    public <T extends PageCommon> T clickCommitNewFielButton(Class<T> clazz){
        driver.findElement(By.id("submit-file")).click();
        Log.message("'SubmitNewFile' button is clicked");

        return pageClassFactory(clazz);
    }

    public NewFilePage selectNewBranchRadioButton(){
        driver.findElement(By.xpath("//input[@type='radio'][contains(@name,'commit')]/parent::*[.//*[contains(text(),'new branch')]]/input")).click();
        Log.message("'New branch' radio button selected" );

        return this;
    }

    public NewFilePage setBranchName(String branchName){
        pageClassFactory(TextBox.class).setTextBox(branchName, By.xpath("//input[contains(@aria-label,'branch name')]"));
        Log.message("'Branch name' is set: '%s'", branchName);

        return this;
    }
}
