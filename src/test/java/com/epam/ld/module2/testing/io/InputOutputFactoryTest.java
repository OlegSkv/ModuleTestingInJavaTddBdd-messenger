package com.epam.ld.module2.testing.io;

import com.epam.ld.module2.testing.TestExecutionInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@ExtendWith(TestExecutionInfo.class)
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

    static Stream<Arguments> stringArrayProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"arg1"}),
                Arguments.of((Object) new String[]{"arg1", "arg2", "arg3"})
        );
    }

    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    void createReaderShouldThrowAnExceptionWithWrongNumberOfAppArguments(String[] args) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new InputOutputFactory().createReader(args));
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
