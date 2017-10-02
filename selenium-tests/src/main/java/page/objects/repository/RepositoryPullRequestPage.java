package page.objects.repository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.WaitFor;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class RepositoryPullRequestPage extends PageCommon {

    @FindBy(xpath = "//a[contains(text(),'New pull')]")
    private WebElement newPullRequestButton;

    public CompareChangesPage clickNewPullRequestButton(){
        newPullRequestButton.click();
        Log.message("'New pull request' button is clicked");

        wait(WaitFor.PageReady(), 20);

        return pageClassFactory(CompareChangesPage.class);
    }
}
