package com.maruszka.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="batch")
public class Batch {

	// annotate the class as an entity and map to db table
	
	// define the fields
	
	// annotate the fields with db column names
	
	// ** set up mapping to InstructorDetail entity
	
	// create constructors
	
	// generate getter/setter methods
	
	// generate toString() method
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="batch_number")
	private int batchNumber;
	
	@Column(name="batch_style")
	private String batchStyle;
	
	@Column(name="batch_name")
	private String batchName;
	
	@Column(name="batch_creation_date", columnDefinition="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date batchCreationDate;
	
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

	public Batch(int batchNumber, String batchStyle, String batchName, Date batchCreationDate) {
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

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
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

	public Date getBatchCreationDate() {
		return batchCreationDate;
	}

	public void setBatchCreationDate(Date batchCreationDate) {
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
