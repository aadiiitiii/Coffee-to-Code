package com.pack.register.model;



import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor	
@Entity
@Table(name = "Register")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userID;
	
	private String userRole="user";
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name="emailid" , unique = true,length=100)
	private String emailID;

	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "account_status")
	private String accountStatus = "unconfirmed";
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "profession")
	private String profession;
	
	@Column(name= "date_of_birth")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	
	@Transient
	Date date = new Date();	
    private Timestamp accountCreationTime = new Timestamp(date.getTime());


	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Password passwordHistory;
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private SecurityAnswer securityAns;

	
}
