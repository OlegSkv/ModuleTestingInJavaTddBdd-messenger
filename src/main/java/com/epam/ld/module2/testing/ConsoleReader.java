package com.epam.ld.module2.testing;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Reads placeholder's names and values from console
 */
public class ConsoleReader implements PlaceholdersReader {

    private final InputStream in;
    private final PrintStream out;


    /**
     * Creates reader with custom input and output streams for testing purpose.
     * @param in stream for input data
     * @param out stream for output data
     */
    public ConsoleReader(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Returns map using console input
     * @return map with key - placeholder name in format #{placeholderName}, value - placeholder content
     */
    @Override
    public Map<String, String> getPlaceholdersToValues() {
        Map<String, String> placeholderByValue = new HashMap<>();
        Scanner scanner = new Scanner(in, StandardCharsets.ISO_8859_1.name());
        boolean isNext = true;
        while (isNext) {
            String placeholder = getInput("Enter placeholder name: ", scanner);
            String value = getInput("Enter value for placeholder #{" + placeholder + "}", scanner);
            placeholderByValue.put("#{" + placeholder + "}", value);
            isNext = isNextInput(scanner);
        }
        return placeholderByValue;
    }

    private String getInput(String userMessage, Scanner scanner) {
        out.println(userMessage);
        return scanner.next();
    }

    private boolean isNextInput(Scanner scanner) {
        out.println("Continue input? y/n:");
        char nextInputAnswer = scanner.next().charAt(0);
        return nextInputAnswer == 'y' || nextInputAnswer == 'Y';
    }
}
