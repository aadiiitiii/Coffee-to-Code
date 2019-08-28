package com.loginportal.deregister.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reviewID;
	@Column(name = "firstname")
	private String firstName;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	@Column(name = "reviews")
	private String review;
	@Column(name = "uid")
	private long uid;
	
	public long getReviewID() {
		return reviewID;
	}
	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
	
