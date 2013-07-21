package mograblog;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * User: guym
 * Date: 7/20/13
 * Time: 7:15 PM
 */
public class MograblogLocator {


    public static class ElementHandler implements MethodInterceptor {
        private static Logger logger = LoggerFactory.getLogger(ElementHandler.class);

        private final ElementLocator locator;
        private WebDriver webDriver = null;
        private Field field;

        private static Set<String> ignoredMethods = new HashSet<String>() {
            {
                add("toString");
                add("hashCode");
            }
        };


        public ElementHandler(Field field, ElementLocator locator, WebDriver webDriver) {
            this.locator = locator;
            this.webDriver = webDriver;
            this.field = field;
            logger.debug("created handler for [{}]", field);
        }

        private WebElement locateElement() {
            return locator.findElement();
        }


        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            if (ignoredMethods.contains(method.getName())) {
                return methodProxy.invokeSuper(o, objects);
            }
            logger.debug("[{}] intercepted method [{}] on object [{}]", field, method, o);
            if (o instanceof MograblogElement) {
                if (!method.getName().equals("setRootElement") && !method.getName().equals("setWebDriver")) {
                    MograblogElement comp = (MograblogElement) o;

                    WebElement element = locateElement();

                    comp.setRootElement(element);
                    comp.setWebDriver(webDriver);
                }

                try {
                    return methodProxy.invokeSuper(o, objects);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }

            } else if (o instanceof WebElement ) {// only handle first displayed
                WebElement displayedElement = locateElement();

                if (displayedElement != null) {
                    logger.info("found first displayed. invoking method");
                    return method.invoke(displayedElement, objects);
                } else {
                    logger.info("unable to detect first displayed");
                }
            }

            return null;

        }

        @Override
        public String toString() {
            return "ElementHandler{" +
                    "field=" + field +
                    '}';
        }
    }
}
