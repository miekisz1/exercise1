package page.objects;

import controls.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.repository.RepositoryCodePage;
import page.objects.repository.RepositoryNavigationPage;
import selenium.framework.common.PageCommon;
import selenium.framework.common.WaitConditions;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class WelcomePage extends PageCommon {

    @FindBy(css = "a[href*='new'][class*='btn']")
    private WebElement startProjectButton;

    public CreateRepositoryPage clickStartProjectButton(){
        return clickStartProjectButton(CreateRepositoryPage.class);
    }

    public WelcomePage setRepositoryFilter(String repositoryName){
        pageClassFactory(TextBox.class).setTextBox(repositoryName, By.id("your-repos-filter"));
        Log.message("'Find repository' control is set: '%s'", repositoryName);

        return this;
    }

    public <T extends PageCommon> T clickRepository(String repositoryName, Class<T> clazz){
        driver.findElement(By.xpath(String.format("//span[@class='repo'][contains(text(),'%s')]", repositoryName))).click();
        Log.message("Repository: '%s' is clicked", repositoryName);

        wait(WaitConditions.elementExists(By.cssSelector("nav a[href*='settings']")), 20);

        return pageClassFactory(clazz);
    }

    public RepositoryCodePage clickRepository(String repositoryName){
        return clickRepository(repositoryName, RepositoryCodePage.class);
    }

    public <T extends PageCommon> T clickStartProjectButton(Class<T> clazz){
        startProjectButton.click();
        Log.message("'Start a project' button is clicked");

        return pageClassFactory(clazz);
    }
}
