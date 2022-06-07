package com.example.demo.model.gifs;

import com.example.demo.model.rates.RatesApp;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;

public class GifApp {
    private RatesApp ratesApp;

    public GifApp() {
        this.ratesApp = new RatesApp();
    }

    /**
     * @param rateName Получает название валюты
     * @return Возвращает URL гифки
     */
    public String getGifUrl(String rateName) {
        String gifTag = this.ratesApp.ratesApp(rateName);
        String apiKey = "uSOi0Am8fLkDaSM08SHPEdvhu5pEENMN";
        GifClient gifClient = Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(GifClient.class, "https://api.giphy.com/v1/gifs/random");
        GifResource gifResource = gifClient.getGif(apiKey, gifTag);
        return gifResource.getData().getImages().getOriginal().getUrl();
    }

    /**
     * @param ratesName Получает название валюты
     * @return Возвращает тег гифки
     */
    public String getGifCategory(String ratesName) {
        String gifTag = this.ratesApp.ratesApp(ratesName);
        return gifTag;
    }
}

