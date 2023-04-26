package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.io.MessagePrinter;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MessengerTest {

    @Test
    void sendMessage() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = mock(TemplateEngine.class);
        MessagePrinter messagePrinter = mock(MessagePrinter.class);
        Client client = mock(Client.class);
        Template template = mock(Template.class);

        Messenger messenger = new Messenger(mailServer, templateEngine, messagePrinter);
        messenger.sendMessage(client, template);

        verify(templateEngine).generateMessage(any(), any());
        verify(mailServer).send(anyString(), anyString());
        verify(messagePrinter).print(anyString(), anyString());
    }
}
