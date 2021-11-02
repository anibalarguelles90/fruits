package com.aag.spring.fruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aag.spring.fruit.model.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
	Fruit findByCode(String code);
}
