package com.assestmanagement.service;

import java.util.List;

import com.assestmanagement.dto.AssestData;
import com.assestmanagement.dto.FixedAssestData;
import com.assestmanagement.model.FixedAssestDataResponse;

public interface AssestService {

	void createAssest(AssestData assestData);

	List<FixedAssestDataResponse> fetchAssestList();

	FixedAssestDataResponse getAssestById(long assestName);

}
