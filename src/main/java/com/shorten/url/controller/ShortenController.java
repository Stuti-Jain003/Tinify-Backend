package com.shorten.url.controller;


import com.shorten.url.dto.ShortenRequest;
import com.shorten.url.service.ShortenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ShortenController {

    private final ShortenService shortenService;

    @PostMapping
    public ResponseEntity<String> createShortenURL(@RequestBody ShortenRequest shortenRequest) {

        String shortenUrl = shortenService.createShortenURL(shortenRequest);
        return new ResponseEntity<>(shortenUrl, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<String> getLongURL(@RequestParam("shortUrl") String shortUrl) {
//        String longURL = shortenService.getLongURL(shortUrl);
//        return new ResponseEntity<>(longURL, HttpStatus.MOVED_PERMANENTLY);
//    }


    @GetMapping("/{shortUrl}")
    public RedirectView redirectUrl(@PathVariable String shortUrl) {
        String longURL = shortenService.getLongURL(shortUrl);
        return new RedirectView(longURL);
    }


}