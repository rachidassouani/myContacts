package org.glsid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Contact implements Serializable{
	 @Id  @GeneratedValue
	private Long id;
	 @NotEmpty
	 @Size(max=30)
	private String firstName;
	 @NotEmpty
	 @Size(max=30)
	private String lastName;
	 @DateTimeFormat(pattern= "yyyy-MM-dd")
	 @NotNull
	private Date dateNaissance;
	 @NotEmpty
	 @Size(max=30)
	private String email;
	 
	private String address;
	public Contact() {
		super();
	}
	public Contact(String firstName, String lastName, Date dateNaissance, String email, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
