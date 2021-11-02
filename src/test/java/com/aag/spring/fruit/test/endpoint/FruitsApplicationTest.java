package com.aag.spring.fruit.test.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.aag.spring.fruit.model.Fruit;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FruitsApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void saveFruit() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", getResourceFile());

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:" + port + "/saveFruit", requestEntity, String.class);

		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, !result.getBody().isEmpty());
	}

	private Resource getResourceFile() {
		return new ClassPathResource("upload.csv");
	}

	@Test
	void fruitsPageable() throws Exception {
		ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:" + port + "/fruits?page=0",
				String.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, !result.getBody().isEmpty());
	}

	@Test
	void fruits() throws Exception {
		ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:" + port + "/fruits", String.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, !result.getBody().isEmpty());
	}

	@Test
	void fruitsByCode() throws Exception {
		Fruit fruit = new Fruit();
		fruit.setCode("cv41235");
		ResponseEntity<Fruit> result = restTemplate.postForEntity("http://localhost:" + port + "/fruit", fruit,
				Fruit.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody() != null);
	}

	@Test
	void updateFruit() throws Exception {
		Fruit fruit = new Fruit();
		fruit.setCode("cv41235");
		fruit.setName("melon");
		final HttpEntity<Fruit> requestEntity = new HttpEntity<Fruit>(fruit, new HttpHeaders());
		final ResponseEntity<Fruit> result = restTemplate.exchange("http://localhost:" + port + "/fruits/cv41235",
				HttpMethod.PUT, requestEntity, Fruit.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody() != null);

	}

}
