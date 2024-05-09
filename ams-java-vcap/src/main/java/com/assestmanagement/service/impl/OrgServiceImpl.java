package com.assestmanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assestmanagement.dto.OrgCreationData;
import com.assestmanagement.model.OrganistationModel;
import com.assestmanagement.repository.OrgRepository;
import com.assestmanagement.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService{
	
	@Autowired
	private OrgRepository orgRepostory;

	@Override
	public OrgCreationData createOrg(OrgCreationData orgCreationData) {
		OrganistationModel organistationModel = new OrganistationModel();
		BeanUtils.copyProperties(orgCreationData, organistationModel);
		orgRepostory.save(organistationModel);
		return orgCreationData;

	}

}
