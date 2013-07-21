package mograblog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * User: guym
 * Date: 7/20/13
 * Time: 7:13 PM
 */
public abstract class MograblogElement {

    protected WebElement rootElement;

    protected WebDriver webDriver;

    public void setRootElement(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
