package com.epam.ld.module2.testing;

import java.util.Map;

public class FileReader implements PlaceholdersReader {

    private final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, String> getPlaceholdersToValues() {
        return null;
    }
}
