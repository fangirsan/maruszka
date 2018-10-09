package com.maruszka.entity;

// http://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
// https://www.thoughts-on-java.org/hibernate-tips-map-bidirectional-many-many-association/

// http://websystique.com/springmvc/springmvc-hibernate-many-to-many-example-annotation-using-join-table/

import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="batch")
public class Batch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="{NotNull.batch.batchNumber}")
	@Min(value=1, message="{Min.batch.batchNumber}")
	@Column(name="batch_number")
	private Integer batchNumber;
	
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	@Column(name="batch_style")
	private String batchStyle;
	
	@Column(name="batch_name")
	private String batchName;
	
	@Column(name="batch_creation_date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate  batchCreationDate;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
		name="batch_malt",
		joinColumns=@JoinColumn(name="batch_id"),
		inverseJoinColumns=@JoinColumn(name="malt_id")
		)
	private List<Malt> malts;
	
	public Batch() {
		
	}

	public Batch(int batchNumber, String batchStyle, String batchName, LocalDate  batchCreationDate) {
		this.batchNumber = batchNumber;
		this.batchStyle = batchStyle;
		this.batchName = batchName;
		this.batchCreationDate = batchCreationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBatchStyle() {
		return batchStyle;
	}

	public void setBatchStyle(String batchStyle) {
		this.batchStyle = batchStyle;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public LocalDate  getBatchCreationDate() {
		return batchCreationDate;
	}

	public void setBatchCreationDate(LocalDate  batchCreationDate) {
		this.batchCreationDate = batchCreationDate;
	}

	public List<Malt> getMalts() {
		return malts;
	}

	public void setMalts(List<Malt> malts) {
		this.malts = malts;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchNumber=" + batchNumber + ", batchStyle=" + batchStyle + ", batchName="
				+ batchName + ", batchCreationDate=" + batchCreationDate + ", malts=" + malts + "]";
	}
	
	// add a convenience method to add malt list
	public void addMalt(Malt theMalt) {
		
		if (malts == null) {
			malts = new ArrayList<>();
		}
		
		malts.add(theMalt);
	}
	
}
