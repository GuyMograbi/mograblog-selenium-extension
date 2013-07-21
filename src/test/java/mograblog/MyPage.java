package mograblog;

import mograblog.elements.MograblogSelect;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: guym
 * Date: 7/21/13
 * Time: 7:59 AM
 */
public class MyPage {

    @FindBy(css="select#testme")
    public MograblogSelect select;

    public void changeToOption2(){
        select.val( "option2" );
    }

    public void changeToOption1(){
        select.val( "option1 ");

    }
}
