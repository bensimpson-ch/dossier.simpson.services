package ch.icyal.ddd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * An aggregateId is the unique attribute(s) of an @Aggregate object.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Documented
public @interface AggregateId {
}
