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
	private String maltManufacturer;

	public MaltManufacturer() {
		
	}

	public MaltManufacturer(int id, String maltManufacturer) {
		this.id = id;
		this.maltManufacturer = maltManufacturer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaltManufacturer() {
		return maltManufacturer;
	}

	public void setMaltManufacturer(String maltManufacturer) {
		this.maltManufacturer = maltManufacturer;
	}

	@Override
	public String toString() {
		return "MaltManufacturer [id=" + id + ", maltManufacturer=" + maltManufacturer + "]";
	}
}
