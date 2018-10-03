package com.maruszka.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="malt_manufacturer")
public class MaltManufacturer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	@Column(name="manufacturer_name")
	private String manufacturerName;

	public MaltManufacturer() {
		
	}

	public MaltManufacturer(int id, String manufacturerName) {
		this.id = id;
		this.manufacturerName = manufacturerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	@Override
	public String toString() {
		return "MaltManufacturer [id=" + id + ", manufacturerName=" + manufacturerName + "]";
	}
}
