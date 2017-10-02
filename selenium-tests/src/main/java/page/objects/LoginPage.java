package page.objects;

import controls.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.framework.common.PageCommon;
import selenium.framework.common.others.Log;

/**
 * Created by PLARMIE on 2017-09-29.
 */
public class LoginPage extends PageCommon {

    @FindBy(css = "input[name='commit']")
    private WebElement signInButton;

    public LoginPage setLogin(String login){
        pageClassFactory(TextBox.class).setTextBox(login, By.id("login_field"));
        Log.message("'Login' field is set");

        return this;
    }

    public LoginPage setPassword(String password){
        pageClassFactory(TextBox.class).setTextBox(password, By.id("password"));
        Log.message("'Password' field is set");

        return this;
    }

    public WelcomePage clickSigInButton(){
        return clickSigInButton(WelcomePage.class);
    }

    public <T extends PageCommon> T  clickSigInButton(Class<T> clazz){
        signInButton.click();
        Log.message("'Sign In' button is clicked");

        return pageClassFactory(clazz);
    }
}
