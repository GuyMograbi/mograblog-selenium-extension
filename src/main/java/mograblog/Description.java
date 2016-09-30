package mograblog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by home on 9/30/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD} )
public @interface Description {
    String value();
}
