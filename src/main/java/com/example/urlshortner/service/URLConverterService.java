package com.example.urlshortner.service;

import com.example.urlshortner.comman.IDConverter;
import com.example.urlshortner.repository.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLConverterService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);
    private final URLRepository urlRepository;

    @Autowired
    public URLConverterService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String localURL, String longURL)
    {
        LOGGER.info("shortening {}", longURL);
        Long id = urlRepository.incrementID();
        String uniqueID = IDConverter.INSTANCE.createUniqueID(id);
        urlRepository.saveUrl("url:"+id,longURL);

        String baseString = formatLocalURLFromShortener(localURL);
        String shortenedURL = baseString + uniqueID;
        return shortenedURL;
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
        Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        String longUrl = urlRepository.getUrl(dictionaryKey);
        LOGGER.info("Converting shortened URL back to {}", longUrl);
        System.out.println(longUrl);

        return longUrl;
    }

}
