package ch.icyal.ddd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A repository represents an object that can access and perist an aggregate.
 */
@Target(ElementType.TYPE)
@Documented
public @interface Repository {
}
