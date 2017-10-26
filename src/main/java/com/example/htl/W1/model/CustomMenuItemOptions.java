package com.example.htl.W1.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="custom_menu_item_options")
public class CustomMenuItemOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="custom_menu_item_option_id", columnDefinition="int(11)")
	private long customMenuItemOptionId;
	
	//This will be question like "Please Choose Bread Type"
	@Column(name="question", nullable=false)
	private String questionForChoose;
	
	//This will be like "Single Choice" or "Multi Choice"
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ref_option_type_id", nullable=false, columnDefinition="int(11)")
	private QuestionOptionType questionOptionType;

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="custom_menu_item_options_and_base_item_map", 
			joinColumns = {
					@JoinColumn(name="ref_custom_menu_item_option_id", nullable=false, referencedColumnName="custom_menu_item_option_id", columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_custom_menu_item_option_id"))
			},
			inverseJoinColumns = {
					@JoinColumn(name="ref_base_item_id", nullable=false, referencedColumnName="base_item_id", columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_base_item_id"))
			}
	)
	private Set<BaseItem> listOfAvailableOptions;
	
	//There is no db link up here. This is just to hold the object.
	@Transient
	private Set<Long> listOfSelectedOptions = new HashSet<>();

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="ref_custom_menu_item_id", nullable=false, columnDefinition="int(11)")
	private CustomMenuItem customMenuItemObj;
	
	public long getCustomMenuItemOptionId() {
		return customMenuItemOptionId;
	}

	public void setCustomMenuItemOptionId(long customMenuItemOptionId) {
		this.customMenuItemOptionId = customMenuItemOptionId;
	}

	public String getQuestionForChoose() {
		return questionForChoose;
	}

	public void setQuestionForChoose(String questionForChoose) {
		this.questionForChoose = questionForChoose;
	}

	public QuestionOptionType getQuestionOptionType() {
		return questionOptionType;
	}

	public void setQuestionOptionType(QuestionOptionType questionOptionType) {
		this.questionOptionType = questionOptionType;
	}

	public Set<BaseItem> getListOfAvailableOptions() {
		return listOfAvailableOptions;
	}

	public void setListOfAvailableOptions(Set<BaseItem> listOfAvailableOptions) {
		this.listOfAvailableOptions = listOfAvailableOptions;
	}

	public CustomMenuItem getCustomMenuItemObj() {
		return customMenuItemObj;
	}

	public void setCustomMenuItemObj(CustomMenuItem customMenuItemObj) {
		this.customMenuItemObj = customMenuItemObj;
	}

	public Set<Long> getListOfSelectedOptions() {
		return listOfSelectedOptions;
	}

	public void setListOfSelectedOptions(Set<Long> listOfSelectedOptions) {
		this.listOfSelectedOptions = listOfSelectedOptions;
	}

		
	
	
}
