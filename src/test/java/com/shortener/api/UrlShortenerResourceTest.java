package com.shortener.api;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.shortener.api.model.UrlShortener;
import com.shortener.api.repository.UrlShortenerRepository;
import com.shortener.api.resource.UrlShortenerResource;
import com.shortener.api.service.ConvertUrlService;
import com.shortener.api.service.UrlShortenerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UrlShortenerResourceTest {
	
	@InjectMocks
	UrlShortenerService urlShortenerService;
	
	@InjectMocks
	UrlShortenerResource mockUrlShortenerResource;
	
	@Mock
	UrlShortenerRepository mockRepository;
	
	@Mock
	ConvertUrlService mockConvertUrlService;
	
	private static String ORIGINAL_URL = "https://spring.io/projects/spring-security";

	@Test
	void redirect_fail_not_found_resource() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/url/wdaskjdakjdakdka";
		try {
			restTemplate.getForEntity(url, String.class);
			fail();
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	@Test
	void success_find_resource() {
		
		Long id = 10L;
		when(mockConvertUrlService.decode("k")).thenReturn((long) id);
		
		UrlShortener url = new UrlShortener(id, ORIGINAL_URL);
		
		when(mockRepository.findById(id)).thenReturn(Optional.of(url));
		assertEquals(Optional.of(new UrlShortener(id ,ORIGINAL_URL)), urlShortenerService.getOriginalUrl("k"));
	}

	
	
}
