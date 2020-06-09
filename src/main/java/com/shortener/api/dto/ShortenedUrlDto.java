package com.shortener.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

public class ShortenedUrlDto {

	@NotEmpty(message = "URL is required")
	@NotNull(message = "URL is required")
	@URL(message = "The URL entered is invalid")
	private String shortenedUrl;

	public ShortenedUrlDto() {
	}

	public ShortenedUrlDto(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	@Override
	public String toString() {
		return "ShortenedUrlDto [shortenedUrl=" + shortenedUrl + "]";
	}
	
}
