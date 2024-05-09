package com.assestmanagement.configuration;

import org.springframework.batch.item.ItemProcessor;

import com.assestmanagement.model.EmployeeModel;
import com.assestmanagement.model.TestModel;


	public class TestProcessor implements ItemProcessor<TestModel,TestModel>{

		@Override
		public TestModel process(TestModel employee) throws Exception {
		            return employee;
		}

	}