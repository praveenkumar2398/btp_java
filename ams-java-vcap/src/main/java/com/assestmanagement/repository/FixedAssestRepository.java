package com.assestmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assestmanagement.model.AssestModel;
import com.assestmanagement.model.FixedAssest;
import com.assestmanagement.model.FixedAssestDataResponse;

@Repository
public interface FixedAssestRepository extends JpaRepository<FixedAssest,Long>{


}
