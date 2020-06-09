package com.shortener.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.api.model.UrlShortener;
import com.shortener.api.repository.UrlShortenerRepository;

@Service
public class UrlShortenerService {

	@Autowired
	private UrlShortenerRepository repository;
	@Autowired
	private ConvertUrlService convertUrlService;

	public String convertToShortUrl(String originalUrl, String baseUrl) {

		return baseUrl.concat(repository.findByOriginalUrl(originalUrl)
				.orElseGet(() -> saveEncoded(originalUrl))
				.getShortenedUrl());
	}
	
	public Optional<UrlShortener> getOriginalUrl(String shortenedUrl) {
		return repository.findById(convertUrlService.decode(shortenedUrl));
	}

	private UrlShortener saveEncoded(String originalUrl) {
		UrlShortener urlShortener = repository.save(new UrlShortener(originalUrl));
		
		String shortenedUrl = convertUrlService.encode(urlShortener.getId());
		urlShortener.setShortenedUrl(shortenedUrl);
		repository.save(urlShortener);

		return urlShortener;
	}

}
