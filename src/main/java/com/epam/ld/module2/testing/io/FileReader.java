package com.epam.ld.module2.testing.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Reads placeholder's names and values from a file<br>
 * Each line in the file should contain one placeholder and value. For example: #{placeholderName}=value
 */
public class FileReader implements PlaceholdersReader {

    private final String filePath;


    /**
     * @param absoluteFilePath absolute path to a file with input data
     */
    public FileReader(String absoluteFilePath) {
        this.filePath = absoluteFilePath;
    }

    /**
     * Returns map using filePath for reading data
     * @return map with key - placeholder name in format #{placeholderName}, value - placeholder content
     */
    @Override
    public Map<String, String> getPlaceholdersToValues() {
        Map<String, String> map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                String regex = "#\\{.+?}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String placeholder = matcher.group();
                    String value = line.substring(matcher.end() + 1);
                    map.put(placeholder, value);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
