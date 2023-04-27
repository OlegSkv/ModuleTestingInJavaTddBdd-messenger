package com.epam.ld.module2.testing.template;

/**
 * The type Template.
 */
public class TemplateImpl implements Template {
    private final String template;

    /**
     * @param template message template
     */
    public TemplateImpl(String template) {
        this.template = template;
    }


    /**
     * @return message template
     */
    @Override
    public String getTemplate() {
        return template;
    }
}
