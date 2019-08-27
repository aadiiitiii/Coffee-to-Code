package com.loginportal.deregister.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="deactivate")
public class Deactivation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="deactivate_id")
	private long deactivateID;
	
	@Column(name="user_id")
	private long userId;
	
	public long getUserId() {
		return userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (deactivateID ^ (deactivateID >>> 32));
		result = prime * result + ((deactivateTime == null) ? 0 : deactivateTime.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Deactivation other = (Deactivation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deactivateID != other.deactivateID)
			return false;
		if (deactivateTime == null) {
			if (other.deactivateTime != null)
				return false;
		} else if (!deactivateTime.equals(other.deactivateTime))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Transient
	Date date = new Date();	
    private Timestamp deactivateTime = new Timestamp(date.getTime());
	

}
