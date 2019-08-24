package com.pack.register.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PasswordHistory")
public class Password implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (passId ^ (passId >>> 32));
		result = prime * result + ((pwd1 == null) ? 0 : pwd1.hashCode());
		result = prime * result + ((pwd2 == null) ? 0 : pwd2.hashCode());
		result = prime * result + ((pwd3 == null) ? 0 : pwd3.hashCode());
		result = prime * result + ((salt1 == null) ? 0 : salt1.hashCode());
		result = prime * result + ((salt2 == null) ? 0 : salt2.hashCode());
		result = prime * result + ((salt3 == null) ? 0 : salt3.hashCode());
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
		Password other = (Password) obj;
		if (passId != other.passId)
			return false;
		if (pwd1 == null) {
			if (other.pwd1 != null)
				return false;
		} else if (!pwd1.equals(other.pwd1))
					return false;
		
		if (salt1 == null) {
			if (other.salt1 != null)
				return false;
		} else if (!salt1.equals(other.salt1))
					return false;
		
		return true;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long passId;
	@Column(name = "password1")
	private String pwd1;// Current password
	@Column(name = "salt1")
	private String salt1;

	@Column(name = "password2")
	private String pwd2;
	@Column(name="salt2")
	private String salt2;

	@Column(name = "password3")
	private String pwd3;
	@Column(name = "salt3")
	private String salt3;


	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	public String getSalt1() {
		return this.salt1;
	}
	public void setSalt1(String salt1) {
		this.salt1 = salt1;
	}
	public String getPwd2() {
		return this.pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	public String getSalt2() {
		return this.salt2;
	}
	public void setSalt2(String salt2) {
		this.salt2 = salt2;
	}
	public String getPwd3() {
		return this.pwd3;
	}
	public void setPwd3(String pwd3) {
		this.pwd3 = pwd3;
	}
	public String getSalt3() {
		return this.salt3;
	}
	public void setSalt3(String salt3) {
		this.salt3 = salt3;
	}
}
