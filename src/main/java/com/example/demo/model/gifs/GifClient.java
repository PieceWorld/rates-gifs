package com.example.demo.model.gifs;

import feign.Param;
import feign.RequestLine;

public interface GifClient {
    /**
     * @param api_key Принимает api_key
     * @param gifTag  Принимает тег гифки
     * @return Возвращает GET запрос на гифку
     */
    @RequestLine("GET?api_key={api_key}&tag={gifTag}")
    GifResource getGif(@Param("api_key") String api_key,
                       @Param("gifTag") String gifTag);
}
