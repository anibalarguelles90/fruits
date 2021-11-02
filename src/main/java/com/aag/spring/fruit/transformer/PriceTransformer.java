package com.aag.spring.fruit.transformer;

import java.util.ArrayList;
import java.util.List;

import com.aag.spring.fruit.model.Fruit;
import com.aag.spring.fruit.model.Price;

public class PriceTransformer {
	
	public static List<Price> generatePrices(List<String> record, Fruit fruit) {
		List<String> recordTmp = new ArrayList<>(record);
		// Se eliminan las dos primeras posiciones que corresponden a clave y nombre
		recordTmp.remove(0);
		recordTmp.remove(0);
		int count = 1;
		List<Price> prices = new ArrayList<>();
		for (String amount : recordTmp) {
			if (count == 1) {
				prices.add(new Price(Double.parseDouble(amount), "kilogram", fruit));
			} else if (count == 2) {
				prices.add(new Price(Double.parseDouble(amount), "half a kilogram", fruit));
			} else if (count == 3) {
				prices.add(new Price(Double.parseDouble(amount), "dozen", fruit));
			} else {
				// Manejar mas casos de precios
			}
			count += 1;
		}
		return prices;
	}
}
