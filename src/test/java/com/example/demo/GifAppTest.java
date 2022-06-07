package com.example.demo;

import com.example.demo.model.gifs.GifApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GifAppTest {
    public GifApp gifApp;

    public GifAppTest(){
        this.gifApp = new GifApp();
    }

    @Test
    void getGifUrlTest() {
       assertNotNull(this.gifApp.getGifUrl("RUB"));
    }
}
