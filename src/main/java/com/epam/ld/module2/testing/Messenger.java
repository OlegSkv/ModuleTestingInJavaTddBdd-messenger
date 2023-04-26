package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.io.MessagePrinter;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

/**
 * The type Messenger.
 */
public class Messenger {
    private final MailServer mailServer;
    private final TemplateEngine templateEngine;
    private final MessagePrinter messagePrinter;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     * @param messagePrinter the output source
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine, MessagePrinter messagePrinter) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
        this.messagePrinter = messagePrinter;
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public void sendMessage(Client client, Template template) {
        String messageContent =
            templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), messageContent);
        messagePrinter.print(client.getAddresses(), messageContent);
    }
}