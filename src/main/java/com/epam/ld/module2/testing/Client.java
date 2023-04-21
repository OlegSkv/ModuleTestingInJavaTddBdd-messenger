package com.epam.ld.module2.testing;

import java.util.Map;

/**
 * The type Client.
 */
public interface Client {

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    String getAddresses();

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    void setAddresses(String addresses);

    /**
     * @return map with
     *  key - a placeholder name in format #{name}
     *  value - a placeholder value
     */
    Map<String, String> getPlaceholders();
}
