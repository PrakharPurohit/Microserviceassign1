package com.lcwd.user.service.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity 
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	
public User()
	{ super();
	}
	public User(Integer id, String firstName, String lastName, String email) {
		super();
		this.id = id; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		} 
	public Integer getId() {
		return id;
		}
	public void setId(Integer id) 
	{ this.id = id;
	}
	public String getFirstName() {
		return firstName; 
		} 
	@Override public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]"; 
		}
	public void setFirstName(String firstName) { 
		this.firstName = firstName; }
	public String getLastName() {
		return lastName; 
		} 
	public void setLastName(String lastName) {
		this.lastName = lastName;
		} 
	public String getEmail() {
		return email;
		}
	public void setEmail(String email) {
		this.email = email;
		} 
	}
	
