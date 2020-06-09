package com.shortener.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortener.api.model.UrlShortener;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Long>{

	Optional<UrlShortener> findByOriginalUrl(String originalUrl);
	
}
