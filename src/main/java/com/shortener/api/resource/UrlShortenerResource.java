package com.shortener.api.resource;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shortener.api.dto.OriginalUrlDto;
import com.shortener.api.dto.ShortenedUrlDto;
import com.shortener.api.model.UrlShortener;
import com.shortener.api.service.UrlShortenerService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerResource {
	
	@Autowired
	private UrlShortenerService service;

	@ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
	@PostMapping("/create-short")
	public ResponseEntity<ShortenedUrlDto> convertToShortUrl(@Valid @RequestBody OriginalUrlDto originalUrlDto, UriComponentsBuilder uriBuilder) {

		URI uri = uriBuilder.path("api/url/").buildAndExpand("").toUri();
		ShortenedUrlDto shortenedUrlDto = new ShortenedUrlDto(service.convertToShortUrl(originalUrlDto.getOriginalUrl(), uri.toString()));

		return ResponseEntity.ok().body(shortenedUrlDto);
	}

	@GetMapping("/{shortUrl}")
	@ApiIgnore
	public ResponseEntity<?> getAndRedirect(@PathVariable String shortUrl, HttpServletResponse response) {
		
		Optional<UrlShortener> result = service.getOriginalUrl(shortUrl);
		if(result.isPresent()) {
			try {
				response.sendRedirect(result.get().getOriginalUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ResponseEntity.notFound().build();

	}

}
