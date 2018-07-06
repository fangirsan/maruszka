package com.maruszka.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="malt")
public class Malt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="malt_name")
	private String maltName;
	
	@Column(name="malt_manufacturer")
	private String maltManufacturer;
	
	@Column(name="malt_filling")
	private int maltFilling;
	
	@Column(name="malt_ebc")
	private int maltEbc;
	
	@Column(name="malt_usage")
	private String maltUsage;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="batch_malt",
		joinColumns=@JoinColumn(name="malt_id"),
		inverseJoinColumns=@JoinColumn(name="batch_id")
		)
	private List<Batch> batches;
	
	public Malt() {
		
	}

	public Malt(String maltName, String maltManufacturer, int maltFilling, int maltEbc, String maltUsage) {
		this.maltName = maltName;
		this.maltManufacturer = maltManufacturer;
		this.maltFilling = maltFilling;
		this.maltEbc = maltEbc;
		this.maltUsage = maltUsage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaltName() {
		return maltName;
	}

	public void setMaltName(String maltName) {
		this.maltName = maltName;
	}

	public String getMaltManufacturer() {
		return maltManufacturer;
	}

	public void setMaltManufacturer(String maltManufacturer) {
		this.maltManufacturer = maltManufacturer;
	}

	public int getMaltFilling() {
		return maltFilling;
	}

	public void setMaltFilling(int maltFilling) {
		this.maltFilling = maltFilling;
	}

	public int getMaltEbc() {
		return maltEbc;
	}

	public void setMaltEbc(int maltEbc) {
		this.maltEbc = maltEbc;
	}

	public String getMaltUsage() {
		return maltUsage;
	}

	public void setMaltUsage(String maltUsage) {
		this.maltUsage = maltUsage;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	@Override
	public String toString() {
		return "Malt [id=" + id + ", maltName=" + maltName + ", maltManufacturer=" + maltManufacturer + ", maltFilling="
				+ maltFilling + ", maltEbc=" + maltEbc + ", maltUsage=" + maltUsage + ", batches=" + batches + "]";
	}
	
}
