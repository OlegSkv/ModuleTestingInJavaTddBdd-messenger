package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String result = template.getTemplate();
        Map<String, String> clientPlaceholders = client.getPlaceholders();
        Set<String> templatePlaceholders = getTemplatePlaceholders(result);
        comparePlaceholders(clientPlaceholders.keySet(), templatePlaceholders);
        for (Map.Entry<String, String> entry : clientPlaceholders.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private void comparePlaceholders(Set<String> keySet, Set<String> templatePlaceholders) {
        if(!keySet.containsAll(templatePlaceholders)) {
            throw new RuntimeException("Some placeholders are absent.");
        }
    }

    private Set<String> getTemplatePlaceholders(String template) {
        Set<String> templatePlaceholders = new HashSet<>();
        Pattern pattern = Pattern.compile("#\\{.+?}");
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String placeholder = matcher.group();
            templatePlaceholders.add(placeholder);
        }

        return templatePlaceholders;
    }
}
