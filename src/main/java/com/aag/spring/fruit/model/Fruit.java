package com.aag.spring.fruit.model;

import javax.persistence.*;

@Entity
@Table(name = "fruit")
public class Fruit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "clave")
	private String clave;

	@Column(name = "nombre")
	private String nombre;

	public Fruit() {

	}

	public Fruit(String clave, String nombre) {
		this.clave = clave;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", " + (clave != null ? "clave=" + clave + ", " : "") + (nombre != null ? "nombre=" + nombre : "") + "]";
	}

}
