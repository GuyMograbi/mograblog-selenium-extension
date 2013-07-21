package mograblog.elements;

import mograblog.MograblogElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: guym
 * Date: 7/21/13
 * Time: 8:00 AM
 */
public class MograblogSelect extends MograblogElement {

    private static Logger logger = LoggerFactory.getLogger(MograblogSelect.class);

    public WebElement getOption( String str ){
        try{
            return rootElement.findElement(By.cssSelector(String.format("option[value=%s]", str)));
        }catch(Exception e){
            logger.debug("error while getting option", e);
        }
        return null;
    }

    public void val(String str) {
        rootElement.click();
        WebElement option = getOption(str);
        if ( option != null ){
            option.click();
        }else{
            throw new RuntimeException( String.format("no such option [%s]" , str) );
        }
    }
}
