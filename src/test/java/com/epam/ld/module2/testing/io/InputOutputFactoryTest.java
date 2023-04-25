package com.epam.ld.module2.testing.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputOutputFactoryTest {

    @Test
    void createReaderShouldReturnConsoleReaderWithZeroAppArguments() {
        String[] args = {};
        PlaceholdersReader reader = new InputOutputFactory().createReader(args);

        Assertions.assertTrue(reader instanceof ConsoleReader);
    }

    @Test
    void createReaderShouldReturnConsoleReaderWithTwoAppArguments() {
        String[] args = {"inputFilePath", "outputFilePath"};
        PlaceholdersReader reader = new InputOutputFactory().createReader(args);

        Assertions.assertTrue(reader instanceof FileReader);
    }

    @Test
    void createPrinterShouldReturnConsolePrinterWithZeroAppArguments() {
        String[] args = {};
        MessagePrinter printer = new InputOutputFactory().createPrinter(args);

        Assertions.assertTrue(printer instanceof ConsolePrinter);
    }

    @Test
    void createReaderShouldReturnFilePrinterWithTwoAppArguments() {
        String[] args = {"inputFilePath", "outputFilePath"};
        MessagePrinter printer = new InputOutputFactory().createPrinter(args);

        Assertions.assertTrue(printer instanceof FilePrinter);
    }
}