package net.kumbhar.dx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE,
        ElementType.LOCAL_VARIABLE,
        ElementType.METHOD,
        ElementType.PARAMETER})
public @interface Hacks {
    Hack[] value();
}
