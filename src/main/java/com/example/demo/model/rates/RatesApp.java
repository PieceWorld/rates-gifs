package com.example.demo.model.rates;


import feign.Feign;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
public class RatesApp {


    @Value("${rates.url}")
    String urlRates;
    @Value("${api.key.rates}")
    String apiKeyRates;

    public SimpleDateFormat formatter;

    public RatesApp() {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * @return Возвращает вчерашнюю дату
     */
    public String getYesterdayDate() {
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        Date yesterdayDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

        return formatter.format(yesterdayDate);
    }

    /**
     * @param rateName Получает из .json курс актуальны и вчерашний
     * @return Принимает выбранную валюту
     */
    public String ratesApp(String rateName) {
        Date latestDate = new Date();
        RatesClient ratesClient = Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(RatesClient.class, urlRates);
        RatesResource ratesResourceLatest = ratesClient.findByDate(apiKeyRates, this.formatter.format(latestDate));
        RatesResource ratesResourceYesterday = ratesClient.findByDate(apiKeyRates, getYesterdayDate());
        double latestRate = ratesResourceLatest.getRates().get(rateName);
        double yesterdayRate = ratesResourceYesterday.getRates().get(rateName);
        return this.getGifTag(latestRate, yesterdayRate);
    }

    /**
     * @param latestRate    Получает актуальный курс валюты
     * @param yesterdayRate Получает вчерашний курс валюты
     * @return Сравнивает и дает тег гифки
     */
    public static String getGifTag(double latestRate, double yesterdayRate) {
        String gifTag;
        if (latestRate == yesterdayRate) {
            gifTag = "pout";
        } else {
            if (latestRate > yesterdayRate) {
                gifTag = "broke";
            } else {
                gifTag = "rich";
            }
        }
        return gifTag;
    }

    /**
     * @return Возвращает доступный список валют
     */
    public Set<String> ratesArray() {
        RatesClient ratesClient = Feign.builder()
                .client(new OkHttpClient())
                .decoder(new GsonDecoder())
                .target(RatesClient.class, urlRates);
        RatesResource ratesResourceYesterday = ratesClient.findByDate(apiKeyRates, getYesterdayDate());
        return ratesResourceYesterday.getRates().keySet();

    }
}