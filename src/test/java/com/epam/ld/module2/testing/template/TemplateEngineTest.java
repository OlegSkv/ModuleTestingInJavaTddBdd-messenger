package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TemplateEngineTest {

//    The system replaces variable placeholders like #{subject} from a template with values provided at runtime.
    @Test
    public void templatePlaceholdersShouldBeReplacedWithRuntimeValues() {
        Template template = new TemplateImpl("Hi, #{first}. Have a good #{second}");
        Client client = mock(Client.class);
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("#{first}", "Java");
        placeholders.put("#{second}", "day");
        when(client.getPlaceholders()).thenReturn(placeholders);

        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(template, client);

        Assertions.assertEquals("Hi, Java. Have a good day", message);
    }
}
