package com.epam.ld.module2.testing.io;

/**
 * Creates a reader depending on the application args
 */
public class InputOutputFactory {

    /**
     * @param args application args
     * @return console or file reader
     */
    public PlaceholdersReader createReader(String[] args) {
        if (args.length == 0) {
            return new ConsoleReader();
        } else if (args.length == 2) {
            return new FileReader(args[0]);
        } else {
            throw new IllegalArgumentException("Zero or two argument is required to run this application");
        }
    }

    /**
     * @param args application args
     * @return console or file printer
     */
    public MessagePrinter createPrinter(String[] args) {
        if (args.length == 0) {
            return new ConsolePrinter();
        } else if (args.length == 2) {
            return new FilePrinter(args[1]);
        } else {
            throw new IllegalArgumentException("Zero or two argument is required to run this application");
        }
    }
}
