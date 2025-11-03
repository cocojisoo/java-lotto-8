package lotto.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Validator {
    private Validator() {
    }

    public static void requireNonBlank(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireNumeric(String input, String errorMessage) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }

    public static void requireNonNegative(long value, String errorMessage) {
        if (value < 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireThousandUnit(long value, String errorMessage) {
        if (value % 1_000 != 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireSize(Collection<?> values, int expected, String errorMessage) {
        if (values.size() != expected) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireNoDuplicates(Collection<?> values, String errorMessage) {
        Set<Object> seen = new HashSet<>();
        for (Object v : values) {
            if (!seen.add(v)) {
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }
}


