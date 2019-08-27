package com.loginportal.deregister.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deactivate")
public class DeRegister {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long deactivate_id;
	private String userRole="user";
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name="emailid" , unique = true,length=100)
	private String emailID;
	
}
