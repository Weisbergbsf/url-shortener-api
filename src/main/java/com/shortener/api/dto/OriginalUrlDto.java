package com.shortener.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

public class OriginalUrlDto {

	@NotEmpty(message = "URL is required")
	@NotNull(message = "URL is required")
	@URL(message = "The URL entered is invalid")
	private String originalUrl;

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	@Override
	public String toString() {
		return "OriginalUrlDto [originalUrl=" + originalUrl + "]";
	}

}
