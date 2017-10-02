package page.objects.repository;

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
public class ConfirmationPopupPage extends PageCommon{

    @FindBy(xpath = "//div[@class='facebox-popup']//button[@type='submit']")
    private WebElement iUnderstandTheConsequencesDeleteThisRepositoryButton;

    public ConfirmationPopupPage setRepositoryName(String text){
        pageClassFactory(TextBox.class).setTextBox(text, By.xpath("//div[@class='facebox-popup']//input[@name='verify']"));
        Log.message("'Repository name' is set: '%s'", text);

        wait(WaitConditions.elementNotExists(By.xpath("//div[@class='facebox-popup']//button[@type='submit'][@disabled]")), 10);

        return this;
    }

    public <T extends PageCommon> T clickIUnderstandTheConsequencesDeleteThisRepositoryButton(Class<T> clazz){
        iUnderstandTheConsequencesDeleteThisRepositoryButton.click();
        Log.message("'I understand the consequences, delete this repository' button is clicked");
        return pageClassFactory(clazz);
    }
}
