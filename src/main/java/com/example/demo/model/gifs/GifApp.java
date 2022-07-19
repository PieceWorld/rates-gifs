package com.example.demo.model.gifs;

import com.example.demo.model.rates.RatesApp;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GifApp {

    @Autowired
    private RatesApp ratesApp;

    @Value("${gifs.url}")
    String urlGifs;
    @Value("${api.key.gifs}")
    String apiKeyGifs;

    /**
     * @param rateName Получает название валюты
     * @return Возвращает URL гифки
     */
    public String getGifUrl(String rateName) {
        String gifTag = this.ratesApp.ratesApp(rateName);
        GifClient gifClient = Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(GifClient.class, urlGifs);
        GifResource gifResource = gifClient.getGif(apiKeyGifs, gifTag);
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

