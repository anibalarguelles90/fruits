package com.aag.spring.fruit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aag.spring.fruit.model.Fruit;
import com.aag.spring.fruit.repository.FruitRepository;
import com.aag.spring.fruit.service.FileReaderService;
import com.aag.spring.fruit.transformer.FruitTransformer;

@RestController
public class FruitsController {

	@Autowired
	FileReaderService fileReaderService;

	@Autowired
	FruitRepository fruitRepository;

	@PostMapping("/saveFruit")
	public List<Fruit> uploadFile(@RequestParam("file") MultipartFile file) {
		List<List<String>> recordsToSave = fileReaderService.readFile(file);
		List<Fruit> entitiesToSave = FruitTransformer.transformToEntities(recordsToSave);
		fruitRepository.saveAll(entitiesToSave);

		return entitiesToSave;
	}

	@GetMapping("/fruits")
	public ResponseEntity<List<Fruit>> getAllfruits(@RequestParam(required = false) Integer page) {
		List<Fruit> fruits = new ArrayList<>();
		try {
			if (page == null) {
				fruitRepository.findAll().forEach(fruits::add);
			}else {
				fruitRepository.findAll(PageRequest.of(page, 5)).forEach(fruits::add);
			}
			

			if (fruits.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(fruits, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(fruits, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/fruit")
	public ResponseEntity<Fruit> getTutorialById(@RequestBody Fruit fruitRequest) {
		Fruit fruit = fruitRepository.findByCode(fruitRequest.getCode());

		if (fruit != null) {
			return new ResponseEntity<>(fruit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/fruits/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			fruitRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
