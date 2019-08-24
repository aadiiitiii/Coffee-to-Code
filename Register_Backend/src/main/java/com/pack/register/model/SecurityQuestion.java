package com.pack.register.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="securityQuestions")
public class SecurityQuestion {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + (int) (questionID ^ (questionID >>> 32));
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
		SecurityQuestion other = (SecurityQuestion) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
					return false;
		if (questionID != other.questionID)
			return false;
		return true;
	}
	@Id
	long questionID;
	String  question;
	public long getQuestionID() {
		return questionID;
	}
	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}
	@Override
	public String toString() {
		return "SecurityQuestion [questionID=" + questionID + ", question=" + question + "]";
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
