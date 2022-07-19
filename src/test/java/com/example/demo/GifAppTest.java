package com.example.demo;

import com.example.demo.model.gifs.GifApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GifAppTest {
    @Autowired
    public GifApp gifApp;

    @Test
    void getGifUrlTest() {
       assertNotNull(this.gifApp.getGifUrl("RUB"));
    }
}
