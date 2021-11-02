package com.aag.spring.fruit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7739052412504685981L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_date", nullable = false, updatable = false)
	@CreatedDate
	private Date cDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "u_date", nullable = false)
	@LastModifiedDate
	private Date uDate;

	
	/**
	 * @return the cDate
	 */
	@JsonIgnore
	@JsonProperty(value = "c_date")
	public Date getcDate() {
		return cDate;
	}

	/**
	 * @param cDate the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	/**
	 * @return the uDate
	 */
	@JsonIgnore
	@JsonProperty(value = "u_date")
	public Date getuDate() {
		return uDate;
	}

	/**
	 * @param uDate the uDate to set
	 */
	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}

}