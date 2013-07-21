package mograblog;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * User: guym
 * Date: 7/21/13
 * Time: 7:57 AM
 */
public class TestSelectElement {

    @Test
    public void shouldHave2Options(){

        WebDriver firefoxDriver = new FirefoxDriver();


        firefoxDriver.get("http://blog.mograbi.info/2009/10/cleaner-java-code-with-cglib.html");
        MyPage page = new MyPage();

        PageFactory.initElements( new MograblogFieldDecorator( firefoxDriver, firefoxDriver ), page );
        page.changeToOption2();

        try{ // normally we don't need this, but we want to see it change this time, so I added sleep
            Thread.sleep(3000);
        }catch(Exception e){}


    }
}
