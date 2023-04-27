package com.epam.ld.module2.testing.condition;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalTime;

/**
 * Test will not be executed in a time range from {@link #fromSecond()}
 * to {@link #toSecond()} based on current system time {@link LocalTime#now()}
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(DisabledForSecondsRangeCondition.class)
public @interface DisabledForSecondsRange {

    /**
     * @return start of range
     */
    String fromSecond();

    /**
     * @return end of range
     */
    String toSecond();
}
