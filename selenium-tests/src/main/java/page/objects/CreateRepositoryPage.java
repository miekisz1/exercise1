package page.objects;

import controls.CheckBox;
import controls.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.WaitConditions;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class CreateRepositoryPage extends PageCommon {

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Create')]")
    private WebElement createReposioryButton;

    public CreateRepositoryPage setRepositoryName(String repositoryName){
        pageClassFactory(TextBox.class).setTextBox(repositoryName, By.id("repository_name"));
        Log.message("'Repository name' is set: '%s'", repositoryName);

        wait(WaitConditions.elementExists(By.cssSelector("*[id='repository_name'][class*='successful']")),20);

        return this;
    }

    public CreateRepositoryPage selectInitializeThisRepositoryCheckBox(boolean selectionStatus){
        pageClassFactory(CheckBox.class).selectCheckBox(selectionStatus, By.id("repository_auto_init"));
        Log.message("'Initialize this repository with a README' checkBox is selected");

        return this;
    }

    public <T extends PageCommon> T clickCreateRepositoryButton(Class<T> clazz){
        createReposioryButton.click();
        Log.message("'Create repository' button is clicked");

        wait(WaitConditions.elementExists(By.cssSelector("nav a[href*='settings']")), 20);

        return pageClassFactory(clazz);
    }
}
