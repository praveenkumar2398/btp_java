package com.assestmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assestmanagement.dto.AssestData;
import com.assestmanagement.dto.Asset;
import com.assestmanagement.model.AssestModel;
import com.assestmanagement.model.AssetType;
import com.assestmanagement.model.Category;
import com.assestmanagement.model.FixedAssest;
import com.assestmanagement.model.FixedAssestDataResponse;
import com.assestmanagement.model.OperationalStatus;
import com.assestmanagement.model.Status;
import com.assestmanagement.repository.AssestRepository;
import com.assestmanagement.repository.FixedAssestRepository;
import com.assestmanagement.service.AssestService;

@Service
public class AssestServiceImpl implements AssestService {

	@Autowired
	private AssestRepository assestRepository;

	@Autowired
	private FixedAssestRepository fixedAssestRepository;

	@Override
	public void createAssest(AssestData assestData) {
		AssestModel assestModel = new AssestModel();
		FixedAssest fixedAssest = new FixedAssest();
		convertAssestDataToModel(assestData,assestModel,fixedAssest);
		assestModel = assestRepository.save(assestModel);
		fixedAssest.setAssest(assestModel);
		fixedAssest = fixedAssestRepository.save(fixedAssest);
		System.out.println("Data saved successfully: " + assestData);
	}

	private void convertAssestDataToModel(AssestData assestData,AssestModel assestModel,FixedAssest fixedAssest) {
		BeanUtils.copyProperties(assestData.getAssest(), assestModel);
		if (assestData.getAssest().getStatus() != null) {
			assestModel.setStatus(Status.valueOf(assestData.getAssest().getStatus().toUpperCase()));
        }
        if (assestData.getAssest().getOperationalStatus() != null) {
        	assestModel.setOperationalStatus(OperationalStatus.valueOf(assestData.getAssest().getOperationalStatus().toUpperCase()));
        }
        if (assestData.getAssest().getAssetType() != null) {
        	assestModel.setAssetType(AssetType.valueOf(assestData.getAssest().getAssetType().toUpperCase()));
        }
        if (assestData.getAssest().getCategory() != null) {
        	assestModel.setCategory(Category.valueOf(assestData.getAssest().getCategory().toUpperCase()));
        }
		BeanUtils.copyProperties(assestData.getFixedassest(), fixedAssest);		
	}

	@Override
	public List<FixedAssestDataResponse> fetchAssestList() {
		List<FixedAssestDataResponse> fixedAssestData = new ArrayList<>();
		List<FixedAssest> fixedAssest = fixedAssestRepository.findAll();
		fixedAssest.stream().forEach(x -> {
			FixedAssestDataResponse populateFixedAssestResponse = populateFixedAssestResponse(x);
			fixedAssestData.add(populateFixedAssestResponse);
		});
		return fixedAssestData;
	}

	@Override
	public FixedAssestDataResponse getAssestById(long assestName) {
		Optional<FixedAssest> findById = fixedAssestRepository.findById(assestName);
		FixedAssestDataResponse populateFixedAssestResponse = populateFixedAssestResponse(findById.get());
		return populateFixedAssestResponse;
	}
	
	public FixedAssestDataResponse populateFixedAssestResponse(FixedAssest fixedAssest){
		FixedAssestDataResponse fixedAssestDataResponse = new FixedAssestDataResponse();
		Asset assest = new Asset();
		BeanUtils.copyProperties(fixedAssest, fixedAssestDataResponse);
		BeanUtils.copyProperties(fixedAssest.getAssest(), assest);
		if (fixedAssest.getAssest().getStatus() != null) {
			assest.setStatus(fixedAssest.getAssest().getStatus().name());
        }
        if (fixedAssest.getAssest().getOperationalStatus() != null) {
        	assest.setOperationalStatus(fixedAssest.getAssest().getOperationalStatus().name());
        }
        if (fixedAssest.getAssest().getAssetType() != null) {
        	assest.setAssetType(fixedAssest.getAssest().getAssetType().name());
        }
        if (fixedAssest.getAssest().getCategory() != null) {
        	assest.setCategory(fixedAssest.getAssest().getCategory().name());
        }
		fixedAssestDataResponse.setAssest(assest);
		return fixedAssestDataResponse;
		
	}

}
