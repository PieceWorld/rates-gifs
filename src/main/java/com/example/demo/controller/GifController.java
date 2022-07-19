package com.example.demo.controller;

import com.example.demo.model.gifs.GifApp;
import com.example.demo.model.rates.RatesApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class GifController {

    @Autowired
    private RatesApp ratesApp;
    @Autowired
    private GifApp gifApp;

    @RequestMapping(value = "/gifPage.html")
    public String gifController(@RequestParam(name = "rate", required = false) String rateName, Model model) {
        String url = this.gifApp.getGifUrl(rateName);
        String gifTag = this.gifApp.getGifCategory(rateName);
        model.addAttribute("url", url);
        model.addAttribute("gifTag", gifTag);
        return "gifPage";
    }

    @RequestMapping(value = "/")
    public String rateController(Model model) {
        Set<String> ratesArray = this.ratesApp.ratesArray();
        model.addAttribute("rates", ratesArray);
        return "index";
    }
}
