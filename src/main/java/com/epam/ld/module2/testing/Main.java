package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.io.InputOutputFactory;
import com.epam.ld.module2.testing.io.MessagePrinter;
import com.epam.ld.module2.testing.io.PlaceholdersReader;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.template.TemplateImpl;

public class Main {

    /**
     * Entry point of the app. There are two ways of running:<br>
     * 1. Without args: placeholders are requested through console
     * <br>For example: java -jar messenger.jar
     * <br>
     * 2. With args: put application args in format: inputFile outputFile.
     * See {@link com.epam.ld.module2.testing.io.FileReader} for inputFile format
     * <br>For example: java -jar messenger.jar C:\test\in.txt C:\out.txt
     *
     * @param args application arguments to start with
     */
    public static void main(String[] args) {
        PlaceholdersReader reader = new InputOutputFactory().createReader(args);
        MessagePrinter printer = new InputOutputFactory().createPrinter(args);
        Client client = new ClientImpl(reader);
        client.setAddresses("user1@mail.com, user2@mail.com");
        MailServer mailServer = new MailServer();
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new TemplateImpl("Hi, #{first}. Have a good #{second}");
        System.out.println("Processing Template: ");
        System.out.println(template.getTemplate());

        new Messenger(mailServer, templateEngine, printer).sendMessage(client, template);
    }
}
