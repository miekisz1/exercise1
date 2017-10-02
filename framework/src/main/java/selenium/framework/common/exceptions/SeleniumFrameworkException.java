package selenium.framework.common.exceptions;

import org.openqa.selenium.WebDriverException;

public class SeleniumFrameworkException extends Exception {
    public SeleniumFrameworkException(final String message){
        super(message);
    }
}
