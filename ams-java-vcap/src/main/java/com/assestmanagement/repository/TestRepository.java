package com.assestmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assestmanagement.model.RoleModel;
import com.assestmanagement.model.TestModel;

@Repository
public interface TestRepository extends JpaRepository<TestModel,String>{

}
