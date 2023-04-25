package com.epam.ld.module2.testing.io;

import java.io.PrintStream;

public class ConsolePrinter implements MessagePrinter {

    private final PrintStream out;

    /**
     * Creates printer with standard output stream
     */
    public ConsolePrinter() {
        this.out = System.out;
    }

    /**
     * Creates printer with custom output stream for testing purpose.
     * @param out stream for output data
     */
    public ConsolePrinter(PrintStream out) {
        this.out = out;
    }

    /**
     * Prints data to console
     * @param addresses client addresses
     * @param messageContent text of the message
     */
    @Override
    public void print(String addresses, String messageContent) {
    }
}
