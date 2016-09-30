package mograblog;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class MograblogFieldDecorator implements FieldDecorator {

    final DefaultFieldDecorator defaultFieldDecorator;

    final SearchContext searchContext;
    private final WebDriver webDriver;


    public MograblogFieldDecorator( SearchContext searchContext, WebDriver webDriver ) {
        this.searchContext = searchContext;
        this.webDriver = webDriver;
        defaultFieldDecorator = new DefaultFieldDecorator( new DefaultElementLocatorFactory( searchContext ) );
    }


    public Object getEnhancedObject( Class clzz, MethodInterceptor methodInterceptor  ){
        Enhancer e = new Enhancer();
        e.setSuperclass(clzz);
        e.setCallback( methodInterceptor );
        return e.create();
    }


    public Object decorate( ClassLoader loader, Field field ) {
        if ( MograblogElement.class.isAssignableFrom( field.getType() )  && field.isAnnotationPresent( FindBy.class )) {
            MograblogElement me = (MograblogElement) getEnhancedObject( field.getType(), getElementHandler( field ) );
            if ( field.isAnnotationPresent(Description.class)) {
                try {
                    MograblogElement.class.getDeclaredField("description").set(me,field.getAnnotation(Description.class).value());
                }catch(Exception e){
                    throw new RuntimeException("unable to set description",e);
                }
            }
            me.getDescription();
            return me;
            
        }else{
            return defaultFieldDecorator.decorate( loader, field );
        }
    }

    private MograblogLocator.ElementHandler getElementHandler( Field field ) {
        return new MograblogLocator.ElementHandler( field, getLocator( field ), webDriver );
    }

    private ElementLocator getLocator( Field field ) {
        return new DefaultElementLocatorFactory( searchContext ).createLocator( field );
    }

}