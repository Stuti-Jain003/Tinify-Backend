package com.example.urlshortner.controller;

import com.example.urlshortner.comman.URLValidator;
import com.example.urlshortner.service.URLConverterService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private final URLConverterService urlConverterService;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @PostMapping("/shortener")
    public String shortenUrl(@RequestBody ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            String shortCode = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: " + shortCode);
            return shortCode;  // Return only the short code
        }
        throw new Exception("Please enter a valid URL");
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String id,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.info("Received shortened url to redirect: " + id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        LOGGER.info("Original URL: " + redirectUrlString);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }
}

class ShortenRequest{
    private String url;

    @JsonCreator
    public ShortenRequest() {

    }

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

