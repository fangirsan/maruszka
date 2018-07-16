package com.maruszka.entity;

import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="hop")
public class Hop {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull(message="This field is required")
	@Size(min=1, max=15, message="The hop name must not be null")
	@Column(name="hop_name")
	private String hopName;
	
	@Column(name="alpha_acid_min")
	private BigDecimal alphaAcidMin;
	
	@DecimalMin(value="1", message="Value must be greater than 0")
	@NotNull(message="This field is required")
	@Column(name="alpha_acid_max")
	private BigDecimal alphaAcidMax;
	
	@NotNull(message="This field is required")
	@Column(name="hop_taste")
	private String hopTaste;
	
	@NotNull(message="This field is required")
	@Column(name="hop_aroma")
	private String hopAroma;
	
	@NotNull(message="This field is required")
	@Column(name="hop_origin")
	private String hopOrigin;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="batch_hop",
			joinColumns=@JoinColumn(name="hop_id"),
			inverseJoinColumns=@JoinColumn(name="batch_id")
			)
	private List<Batch> batches;
	
	public Hop() {
		
	}

	public Hop(String hopName, BigDecimal alphaAcidMax, String hopTaste, String hopAroma, String hopOrigin) {
		this.hopName = hopName;
		this.alphaAcidMax = alphaAcidMax;
		this.hopTaste = hopTaste;
		this.hopAroma = hopAroma;
		this.hopOrigin = hopOrigin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHopName() {
		return hopName;
	}

	public void setHopName(String hopName) {
		this.hopName = hopName;
	}

	public BigDecimal getAlphaAcidMin() {
		return alphaAcidMin;
	}

	public void setAlphaAcidMin(BigDecimal alphaAcidMin) {
		this.alphaAcidMin = alphaAcidMin;
	}

	public BigDecimal getAlphaAcidMax() {
		return alphaAcidMax;
	}

	public void setAlphaAcidMax(BigDecimal alphaAcidMax) {
		this.alphaAcidMax = alphaAcidMax;
	}

	public String getHopTaste() {
		return hopTaste;
	}

	public void setHopTaste(String hopTaste) {
		this.hopTaste = hopTaste;
	}

	public String getHopAroma() {
		return hopAroma;
	}

	public void setHopAroma(String hopAroma) {
		this.hopAroma = hopAroma;
	}

	public String getHopOrigin() {
		return hopOrigin;
	}

	public void setHopOrigin(String hopOrigin) {
		this.hopOrigin = hopOrigin;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	
}
