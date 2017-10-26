package com.example.htl.W1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question_option_types")
public class QuestionOptionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="question_option_type_id", columnDefinition="int(11)")
	private long id;
	
	@Column(name = "question_option_type_name")
	private String quetionOptionTypeName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuetionOptionTypeName() {
		return quetionOptionTypeName;
	}

	public void setQuetionOptionTypeName(String quetionOptionTypeName) {
		this.quetionOptionTypeName = quetionOptionTypeName;
	}
	
	

}
