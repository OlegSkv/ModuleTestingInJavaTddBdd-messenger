package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.ClientImpl;
import com.epam.ld.module2.testing.TestExecutionInfo;
import com.epam.ld.module2.testing.io.ConsoleReader;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(TestExecutionInfo.class)
@EnableRuleMigrationSupport
public class TemplateEngineTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Template template;
    private TemplateEngine templateEngine;

    @BeforeEach
    public void setup() {
        template = new TemplateImpl("Hi, #{first}. Have a good #{second}");
        templateEngine = new TemplateEngine();
    }

    @Test
    public void templatePlaceholdersShouldBeReplacedWithRuntimeValues() {
        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("first\nJava\ny\nsecond\nday\nn".getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader reader = new ConsoleReader(in, out);
        Client client = new ClientImpl(reader);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }

    @Test
    public void templateGeneratorShouldThrowAnExceptionWhenAtLeastOnePlaceholderAbsent() {
        thrown.expect(RuntimeException.class);

        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("first\nJava\nn".getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader reader = new ConsoleReader(in, out);
        Client client = new ClientImpl(reader);

        templateEngine.generateMessage(template, client);
    }

    @Test
    public void templateGeneratorShouldThrowAnExceptionWhenTwoPlaceholdersAbsent() {
        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("dummyInput\ndummyData\nn".getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader reader = new ConsoleReader(in, out);
        Client client = new ClientImpl(reader);

        assertThrows(RuntimeException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void templateGeneratorIgnoresRuntimePlaceholderValuesWhichIsNotInTheTemplate() {
        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("first\nJava\ny\nsecond\nday\ny\nabsentInTemplate\nRedundant value\nn"
                        .getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader reader = new ConsoleReader(in, out);
        Client client = new ClientImpl(reader);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }

    @Test
    public void templateGeneratorShouldSupportSpecialPlaceholderValues() {
        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("first\nJava\ny\nsecond\n#{specialValue}\nn"
                        .getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader reader = new ConsoleReader(in, out);
        Client client = new ClientImpl(reader);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good #{specialValue}", message);
    }
}
