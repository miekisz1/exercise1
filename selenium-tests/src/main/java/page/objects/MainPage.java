package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.others.Log;


public class MainPage extends PageCommon {

    @FindBy(css="a[href*='login']")
    private WebElement signInLink;

    public LoginPage clickSignInLink(){
        signInLink.click();
        Log.message("'SignIn' link is clicked");

        return pageClassFactory(LoginPage.class);
    }
}
