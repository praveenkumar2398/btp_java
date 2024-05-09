package com.assestmanagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assestmanagement.dto.EmployeeData;
import com.assestmanagement.dto.LoginRequestData;
import com.assestmanagement.dto.LoginResponse;

public interface EmployeeService {

	 EmployeeData createUser(EmployeeData employeeData);

	void fetchByEmployeeId(String id);

	void createEmployee(EmployeeData employeeData);


	LoginResponse authenticateGenerateToken(LoginRequestData employeeData);

	String importEmployeeData(MultipartFile fileData);

	List<EmployeeData> getEmployeeList();

	void deleteEmployee(List<String> employeeIds);

	EmployeeData getEmployeeById(String employeeId);

	void updateEmployee(EmployeeData employeeData);

}
