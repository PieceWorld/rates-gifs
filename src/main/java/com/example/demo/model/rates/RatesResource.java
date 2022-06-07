package com.example.demo.model.rates;

import lombok.Data;

import java.util.HashMap;

@Data
public class RatesResource {
    private HashMap<String, Double> rates;

}
