package com.epam.ld.module2.testing.io;

/**
 * Prints a message
 */
public interface MessagePrinter {

    /**
     * Print data to a specific source.
     * @param addresses client addresses
     * @param messageContent text of the message
     */
    void print(String addresses, String messageContent);
}
