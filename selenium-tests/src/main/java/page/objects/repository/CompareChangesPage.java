package page.objects.repository;

import controls.ComboBox;
import org.openqa.selenium.By;
import selenium.framework.common.PageCommon;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class CompareChangesPage extends PageCommon {

    public CompareChangesPage selectBranchToCompae(String branchName){
        pageClassFactory(ComboBox.class).selectItem(branchName, By.xpath("//button[.//i[contains(text(),'compare')]]"));
        Log.message("Branch to compare is selected");

        return this;
    }

    public CompareChangesPage clickCreatePullRequestButton(){
        driver.findElement(By.xpath("//button[contains(text(),'Create pull')]")).click();
        Log.message("'Create pull request' button is clicked");

        return this;
    }
}
