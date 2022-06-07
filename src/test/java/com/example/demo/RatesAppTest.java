package com.example.demo;

import com.example.demo.model.rates.RatesApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RatesAppTest {
    public RatesApp ratesApp;

    @Test
    void getGifTagTest() {
        assertEquals("broke", RatesApp.getGifTag(10.0, 2.0));
    }

    public RatesAppTest() {
        this.ratesApp = new RatesApp();
    }

    @Test
    void ratesArrayTest() {
        assertNotNull(this.ratesApp.ratesArray());
    }
}
