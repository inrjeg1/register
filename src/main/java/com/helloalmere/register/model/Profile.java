package com.helloalmere.register.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Entity
@Table(name="profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "first_name", "last_name", "email", "phone_number", "category", "proficiency" })
public class Profile {
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column(name="first_name",nullable=false)
	@JsonProperty("first_name")
	private String firstName;
	
	@Column(name="last_name",nullable=false)
	@JsonProperty("last_name")
	private String lastName;
	
	@Column(name="email",nullable=false)
	@JsonProperty("email")
	private String email;
	
	@Column(name="phone_number",nullable=false)
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	@JsonProperty("category")
	private Category category;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="proficiency_id")
	@JsonProperty("proficiency")
	private Proficiency proficiency;
	
	
	/** The created at. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt = new Date();
	
	

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phone_number")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("category")
	public Category getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonProperty("proficiency")
	public Proficiency getProficiency() {
		return proficiency;
	}

	@JsonProperty("proficiency")
	public void setProficiency(Proficiency proficiency) {
		this.proficiency = proficiency;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
     * On create.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();        
    }

	
	
	

}