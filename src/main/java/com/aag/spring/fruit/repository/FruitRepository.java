package com.aag.spring.fruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aag.spring.fruit.model.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
	List<Fruit> findByClaveContaining(String clave);
}
