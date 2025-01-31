package com.loginportal.deregister.model;



import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.loginportal.deregister.model.UserStatus;
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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountCreationTime == null) ? 0 : accountCreationTime.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((passwordHistory == null) ? 0 : passwordHistory.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((securityAns == null) ? 0 : securityAns.hashCode());
		result = prime * result + (int) (userID ^ (userID >>> 32));
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accountCreationTime == null) {
			if (other.accountCreationTime != null)
				return false;
		} else if (!accountCreationTime.equals(other.accountCreationTime))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (passwordHistory == null) {
			if (other.passwordHistory != null)
				return false;
		} else if (!passwordHistory.equals(other.passwordHistory))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (securityAns == null) {
			if (other.securityAns != null)
				return false;
		} else if (!securityAns.equals(other.securityAns))
			return false;
		if (userID != other.userID)
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		if (userStatus == null) {
			if (other.userStatus != null)
				return false;
		} else if (!userStatus.equals(other.userStatus))
			return false;
		return true;
	}

	public boolean newFn(User other) {
		if (passwordHistory == null) {
			if (other.passwordHistory != null)
				return false;
		} else if (!passwordHistory.equals(other.passwordHistory))
					return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
					return false;
		if (securityAns == null) {
			if (other.securityAns != null)
				return false;
		} else if (!securityAns.equals(other.securityAns))
					return false;
		
		return true;
	}
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="user_status")
	private UserStatus userStatus=UserStatus.UNCONFIRMED;
	@Transient
	Date date = new Date();	
    private Timestamp accountCreationTime = new Timestamp(date.getTime());


	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Password passwordHistory;
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private SecurityAnswer securityAns;
	
	public User() {
		//default Constructor
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
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

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getAccountCreationTime() {
		return accountCreationTime;
	}

	public void setAccountCreationTime(Timestamp accountCreationTime) {
		this.accountCreationTime = accountCreationTime;
	}

	public Password getPasswordHistory() {
		return passwordHistory;
	}

	public void setPasswordHistory(Password passwordHistory) {
		this.passwordHistory = passwordHistory;
	}

	public SecurityAnswer getSecurityAns() {
		return securityAns;
	}

	public void setSecurityAns(SecurityAnswer securityAns) {
		this.securityAns = securityAns;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userRole=" + userRole + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailID=" + emailID + ", phoneNo=" + phoneNo + ", emailConfirmationFlag="
				+ userStatus+ ", date=" + date + ", accountCreationTime=" + accountCreationTime
				+ ", passwordHistory=" + passwordHistory + ", securityAns=" + securityAns + "]";
	}
}
