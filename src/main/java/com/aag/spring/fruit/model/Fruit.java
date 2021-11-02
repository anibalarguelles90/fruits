package com.aag.spring.fruit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fruit extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1749741125498296466L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String code;

	private String name;

	@OneToMany(mappedBy = "fruit", cascade = CascadeType.ALL)
	private List<Price> prices;

	/**
	 * 
	 */
	public Fruit() {
		super();
	}

	/**
	 * @param key
	 * @param name
	 */
	public Fruit(String code, String name) {
		super();
		this.code = code;
		this.name = name;
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

	/**
	 * @return the key
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param key the key to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prices
	 */
	public List<Price> getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

}