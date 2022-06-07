package com.example.demo.model.rates;

import feign.Param;
import feign.RequestLine;

public interface RatesClient {
    @RequestLine("GET{date}.json?app_id={app_id}")
    RatesResource findByDate(@Param("app_id") String app_id, @Param("date") String date);
}
