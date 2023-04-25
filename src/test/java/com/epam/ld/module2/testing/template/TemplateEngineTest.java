package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.ClientImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Client client = new ClientImpl();

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }

    @Test
    public void templateGeneratorShouldThrowAnExceptionWhenAtLeastOnePlaceholderAbsent() {
        thrown.expect(RuntimeException.class);
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholdersToValues = new HashMap<>();
        runtimePlaceholdersToValues.put("#{first}", "Java");
        when(client.getPlaceholdersToValues()).thenReturn(runtimePlaceholdersToValues);

        templateEngine.generateMessage(template, client);
    }

    @Test
    public void templateGeneratorShouldThrowAnExceptionWhenTwoPlaceholdersAbsent() {
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholdersToValues = new HashMap<>();
        when(client.getPlaceholdersToValues()).thenReturn(runtimePlaceholdersToValues);

        assertThrows(RuntimeException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void templateGeneratorIgnoresRuntimePlaceholderValuesWhichIsNotInTheTemplate() {
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholdersToValues = new HashMap<>();
        runtimePlaceholdersToValues.put("#{first}", "Java");
        runtimePlaceholdersToValues.put("#{second}", "day");
        runtimePlaceholdersToValues.put("#{absentInTemplate}", "Redundant value");
        when(client.getPlaceholdersToValues()).thenReturn(runtimePlaceholdersToValues);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }

//    System should support values passed in runtime with #{…}.
    @Test
    public void templateGeneratorShouldSupportSpecialPlaceholderValues() {
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholdersToValues = new HashMap<>();
        runtimePlaceholdersToValues.put("#{first}", "Java");
        runtimePlaceholdersToValues.put("#{second}", "#{specialValue}");
        when(client.getPlaceholdersToValues()).thenReturn(runtimePlaceholdersToValues);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good #{specialValue}", message);
    }
}
