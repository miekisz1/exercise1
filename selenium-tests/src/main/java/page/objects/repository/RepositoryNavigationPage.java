package page.objects.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.WaitConditions;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class RepositoryNavigationPage extends PageCommon {

    @FindBy(css = "nav a[href*='settings']")
    private WebElement settingsMenuItem;

    @FindBy(css = "nav a[href*='pull']")
    private WebElement pullRequestMenuItem;

    public RepositorySettingsPage clickSettingsMenuItem(){
        settingsMenuItem.click();
        Log.message("'Settings' menu item is clicked");

        wait(WaitConditions.elementExists(By.xpath("//button[contains(text(),'Delete')]")), 20);

        return pageClassFactory(RepositorySettingsPage.class);
    }

    public RepositoryPullRequestPage clickPullRequestsMenuItem(){
        pullRequestMenuItem.click();
        Log.message("'Pull request' menu item is clicked");

        wait(WaitConditions.elementExists(By.xpath("//a[contains(text(),'New pull')]")), 20);

        return pageClassFactory(RepositoryPullRequestPage.class);
    }
}
