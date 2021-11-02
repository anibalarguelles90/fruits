package com.aag.spring.fruit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Price extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2228590387356194082L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double amount;
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	private Fruit fruit;

	/**
	 * 
	 */
	public Price() {
		super();
	}

	/**
	 * @param id
	 * @param amount
	 * @param description
	 * @param fruit
	 */
	public Price(double amount, String description, Fruit fruit) {
		super();
		this.amount = amount;
		this.description = description;
		this.fruit = fruit;
		this.setcDate(new Date());
		this.setuDate(new Date());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fruits
	 */
	@JsonIgnore
	@JsonProperty(value = "fruit")
	public Fruit getFruit() {
		return fruit;
	}

	/**
	 * @param fruits the fruits to set
	 */
	public void setFruit(Fruit fruits) {
		this.fruit = fruits;
	}

}