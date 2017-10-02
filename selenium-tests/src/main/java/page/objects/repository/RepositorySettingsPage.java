package page.objects.repository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class RepositorySettingsPage extends PageCommon {

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    private WebElement deleteThisRepositoryButton;

    public ConfirmationPopupPage clickDeleteThisRepositoryButton(){
        deleteThisRepositoryButton.click();
        Log.message("'Delete this repository' button is clicked");

        return pageClassFactory(ConfirmationPopupPage.class);
    }

}
