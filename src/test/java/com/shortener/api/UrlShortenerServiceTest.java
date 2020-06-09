package com.shortener.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.shortener.api.service.ConvertUrlService;

@RunWith(MockitoJUnitRunner.class)
class UrlShortenerServiceTest {
	
	private ConvertUrlService service = new ConvertUrlService();

	@Test
	void success_encode_10_lessThan62() {
		assertEquals("k", service.encode(10));
	}
	
	@Test
	void success_encode_65_moreThan62() {
		assertEquals("bd", service.encode(65));
	}
	
	@Test
	void success_encode_moreThan62() {
		assertEquals("bn", service.encode(75));
		assertEquals("bo", service.encode(76));
		assertEquals("bp", service.encode(77));
		assertEquals("bq", service.encode(78));
		assertEquals("br", service.encode(79));
	}
	
	@Test
	void success_decode_10_lessThan62() {
		assertEquals(10, service.decode("k"));
	}
	
	@Test
	void success_decode_65_moreThan62() {
		assertEquals(65, service.decode("bd"));
	}
	
	@Test
	void success_decode_moreThan62() {
		assertEquals(75, service.decode("bn"));
		assertEquals(76, service.decode("bo"));
		assertEquals(77, service.decode("bp"));
		assertEquals(78, service.decode("bq"));
		assertEquals(79, service.decode("br"));
	}

}
