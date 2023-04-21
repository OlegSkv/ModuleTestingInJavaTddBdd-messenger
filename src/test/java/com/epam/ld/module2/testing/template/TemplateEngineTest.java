package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

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
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholders = new HashMap<>();
        runtimePlaceholders.put("#{first}", "Java");
        runtimePlaceholders.put("#{second}", "day");
        when(client.getPlaceholders()).thenReturn(runtimePlaceholders);

        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }

    //If at least one placeholder value is not provided at runtime – template generator should throw an exception.
    @Test
    public void templateGeneratorShouldThrowAnExceptionWhenAtLeastOnePlaceholderAbsent() {
        thrown.expect(RuntimeException.class);
        Client client = mock(Client.class);
        Map<String, String> runtimePlaceholders = new HashMap<>();
        runtimePlaceholders.put("#{first}", "Java");
        when(client.getPlaceholders()).thenReturn(runtimePlaceholders);

        templateEngine.generateMessage(template, client);
    }
}
