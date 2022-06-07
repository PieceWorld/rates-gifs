package com.example.demo.model.rates;


import feign.Feign;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication

@Component
public class RatesApp {

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
                .target(RatesClient.class, "https://openexchangerates.org/api/historical");
        String appId = "b60ab82d067e44d2b8da0ed3ba6e4e2f";
        RatesResource ratesResourceLatest = ratesClient.findByDate(appId, this.formatter.format(latestDate));
        RatesResource ratesResourceYesterday = ratesClient.findByDate(appId, getYesterdayDate());
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
                .target(RatesClient.class, "https://openexchangerates.org/api/historical");
        String appId = "b60ab82d067e44d2b8da0ed3ba6e4e2f";
        RatesResource ratesResourceYesterday = ratesClient.findByDate(appId, getYesterdayDate());
        return ratesResourceYesterday.getRates().keySet();

    }
}