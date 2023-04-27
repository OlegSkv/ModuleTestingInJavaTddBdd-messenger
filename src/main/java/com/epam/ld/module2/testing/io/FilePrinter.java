package com.epam.ld.module2.testing.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilePrinter implements MessagePrinter {

    private final String filePath;


    public FilePrinter(String absoluteFilePath) {
        this.filePath = absoluteFilePath;
    }

    @Override
    public void print(String addresses, String messageContent) {
        List<String> lines = new ArrayList<>();
        lines.add("************" + LocalDateTime.now() + "*************");
        lines.add("The message with the following content was sent to the addresses:");
        lines.add("\t" + addresses);
        lines.add("");
        lines.add(messageContent);
        lines.add("");

        Path file = Paths.get(filePath);
        try {
            Files.write(file, lines, StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
