package com.assestmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assestmanagement.model.OrganistationModel;

@Repository
public interface OrgRepository extends JpaRepository<OrganistationModel,Long> {

}
