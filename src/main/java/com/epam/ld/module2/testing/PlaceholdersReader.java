package com.epam.ld.module2.testing;

import java.util.Map;

/**
 * Reads placeholder's names and values
 */
public interface PlaceholdersReader {


    /**
     * Gets data from specific source and returns map based on these data.
     * @return map with key - placeholder name in format #{placeholderName}, value - placeholder content
     */
    Map<String, String> getPlaceholdersToValues();
}
