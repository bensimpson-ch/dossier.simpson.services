package ch.icyal.ddd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A ValueObject is an immutable object representing information in a project's domain.
 */
@Target(ElementType.TYPE)
@Documented
public @interface ValueObject {
}
