package ch.icyal.ddd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * An aggregate is a grouping of value objects that form a logical unit.
 */
@Target(ElementType.TYPE)
@Documented
public @interface Aggregate {
}
