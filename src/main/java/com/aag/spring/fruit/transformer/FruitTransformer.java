package com.aag.spring.fruit.transformer;

import java.util.ArrayList;
import java.util.List;

import com.aag.spring.fruit.model.Fruit;
import com.aag.spring.fruit.model.Price;

public class FruitTransformer {

	public static List<Fruit> transformToEntities(List<List<String>> recordsToSave) {
		List<Fruit> fruits = new ArrayList<>();
		for (List<String> record : recordsToSave) {
			if (record.size() > 3)
				fruits.add(generateFruit(record));
		}
		return fruits;
	}

	public static Fruit generateFruit(List<String> record) {
		Fruit fruit = new Fruit(record.get(0), record.get(1));
		List<Price> prices = PriceTransformer.generatePrices(record, fruit);
		fruit.setPrices(prices);
		return fruit;
	}
}
