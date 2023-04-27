package com.epam.ld.module2.testing.condition;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalTime;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

/**
 * {@link ExecutionCondition} that supports the {@code @DisabledForSecondsRange} annotation.
 */
public class DisabledForSecondsRangeCondition implements ExecutionCondition {
    private static final ConditionEvaluationResult ENABLED_BY_DEFAULT =
            enabled("@DisabledForSecondsRange is not present");

    private static final ConditionEvaluationResult DISABLED = disabled("Disabled");
    public static final int MAX_VALUE = 59;
    public static final int MIN_VALUE = 0;

    /**
     * Containers/tests are disabled if {@code @DisabledForSecondsRange} is present on the test class or method and
     * value of current system second is between set range
     */
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return findAnnotation(context.getElement(), DisabledForSecondsRange.class)
                .map(disabledForSecondsRange -> {
                    int from;
                    int to;
                    try {
                        from = Integer.parseInt(disabledForSecondsRange.fromSecond());
                        to = Integer.parseInt(disabledForSecondsRange.toSecond());
                        if (from < MIN_VALUE || to < MIN_VALUE || to > MAX_VALUE || from > to) {
                            throw new IllegalArgumentException(
                                    "'from' and 'to' should be between 0 and 59, 'from' should be greater than 'to'");
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Incorrect input, fr", e);
                    }
                    int currentSecond = LocalTime.now().getSecond();

                    return (currentSecond >= from && currentSecond <= to) ? DISABLED : ENABLED_BY_DEFAULT;

                }).orElse(ENABLED_BY_DEFAULT);
    }
}
