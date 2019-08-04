package net.kumbhar.dx;

import java.lang.annotation.*;

@Repeatable(Hacks.class)
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE,
        ElementType.LOCAL_VARIABLE,
        ElementType.METHOD,
        ElementType.PARAMETER})
/*
 * An annotation that denotes a "creative solution" in code.
 */
public @interface Hack {

    /**
     * Description of this creative solution. Generally speaking, this should document the necessity of this hack
     * and what can be done to remove it.
     *
     * @return Description of the hack
     */
    String description();

    /**
     * Optional: A link or identifier to any bug tracking system that has an item to fix this hack, eventually, of course.
     *
     * @return A url or key in a bug tracker e.g. https://jira.example.com/PROJECT-123
     */
    String trackedBy() default "";

    /**
     * Optional: Probable fix date for this hack i.e. this hack will be cause the build to fail after this date. At such
     * time you either need to :
     * 1. Fix the issue and remove this annotation
     * 2. Update the date to sometime further in the future
     *
     * Supported date format is: YYYY-MM-DD
     *
     * @return Probable fix date for this hack
     */
    String fixBeforeDate() default "";
}
