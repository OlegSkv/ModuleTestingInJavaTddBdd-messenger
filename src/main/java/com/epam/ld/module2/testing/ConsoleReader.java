package com.epam.ld.module2.testing;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ConsoleReader implements PlaceholdersReader {

    private final InputStream in;
    private final PrintStream out;

    public ConsoleReader(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public Map<String, String> getPlaceholdersByValues() {
        Map<String, String> placeholderByValue = new HashMap<>();
        return placeholderByValue;
    }
}
