package com.example.htl.W1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htl.W1.model.QuestionOptionType;
import com.example.htl.W1.repository.QuestionOptionTypeRepository;

@Service
public class QuestionOptionTypeServiceImpl implements QuestionOptionTypeService {

	@Autowired
	private QuestionOptionTypeRepository questionOptionTypeRepository;

	@Override
	public List<QuestionOptionType> getByAll() {
		return questionOptionTypeRepository.findAll();
	}
}
