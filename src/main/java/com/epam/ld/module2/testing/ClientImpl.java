package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.io.ConsoleReader;

import java.util.Map;

/**
 * The type Client.
 */
public class ClientImpl implements Client {

    private final ConsoleReader reader;
    private String addresses;

    public ClientImpl(ConsoleReader reader) {
        this.reader = reader;
    }

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    @Override
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    @Override
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    /**
     * @return map with
     * key - placeholder name
     * value - placeholder value
     */
    @Override
    public Map<String, String> getPlaceholdersToValues() {
        return reader.getPlaceholdersToValues();
    }
}
