package com.aag.spring.fruit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aag.spring.fruit.model.Fruit;
import com.aag.spring.fruit.repository.FruitRepository;

@RestController
public class FruitsController {

	@Autowired
	FruitRepository tutorialRepository;

	@GetMapping("/fruits")
	public ResponseEntity<List<Fruit>> getAllfruits(@RequestParam(required = false) String title) {
		try {
			List<Fruit> fruits = new ArrayList<Fruit>();

			if (title == null)
				tutorialRepository.findAll().forEach(fruits::add);
			else
				tutorialRepository.findByClaveContaining(title).forEach(fruits::add);

			if (fruits.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(fruits, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fruits/{id}")
	public ResponseEntity<Fruit> getTutorialById(@PathVariable("id") long id) {
		Optional<Fruit> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/fruits")
	public ResponseEntity<Fruit> createTutorial(@RequestBody Fruit tutorial) {
		try {
			Fruit _tutorial = tutorialRepository.save(new Fruit(tutorial.getClave(), tutorial.getNombre()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/fruits/{id}")
	public ResponseEntity<Fruit> updateTutorial(@PathVariable("id") long id, @RequestBody Fruit tutorial) {
		Optional<Fruit> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Fruit fruit = tutorialData.get();
			fruit.setClave(tutorial.getClave());
			fruit.setNombre(tutorial.getNombre());
			return new ResponseEntity<>(tutorialRepository.save(fruit), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/fruits/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/fruits")
	public ResponseEntity<HttpStatus> deleteAllfruits() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
