package com.assestmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assestmanagement.constant.AssestManagementConstants;
import com.assestmanagement.dto.EmployeeData;
import com.assestmanagement.dto.ErrorResponseDto;
import com.assestmanagement.dto.ResponseDto;
import com.assestmanagement.model.FixedAssestDataResponse;
import com.assestmanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "HTTP Status CREATED"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> createEmployee(@RequestBody EmployeeData employeeData) {
    	employeeService.createEmployee(employeeData);
    	return ResponseEntity.status(HttpStatus.CREATED)
    			.body(new ResponseDto(AssestManagementConstants.STATUS_201, AssestManagementConstants.MESSAGE_201));
	}
    
    @RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeData>> getEmployeeList() {
    	List<EmployeeData> employeeList = employeeService.getEmployeeList();
    	return ResponseEntity.ok().body(employeeList);
	}
    
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.PUT)
  	public ResponseEntity<ResponseDto> deleteEmployee(@RequestBody List<String> employeeIds) {
    	employeeService.deleteEmployee(employeeIds);
      	return ResponseEntity.status(HttpStatus.CREATED)
    			.body(new ResponseDto(AssestManagementConstants.STATUS_204, AssestManagementConstants.MESSAGE_204));
  	}
    
    @RequestMapping(value="/importEmployeeData", method = RequestMethod.POST)
    public ResponseEntity<String> importEmployee(@RequestParam MultipartFile fileData) {
    	String importEmployeeData = employeeService.importEmployeeData(fileData);
        return ResponseEntity.ok(importEmployeeData);
    }
 
    @RequestMapping(value="/getEmployeeById/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeData> getAssestByid(@PathVariable("employeeId") String employeeId){
    	EmployeeData assestById = employeeService.getEmployeeById(employeeId);
    	return ResponseEntity.ok().body(assestById);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
   	public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeData employeeData) {
       	employeeService.updateEmployee(employeeData);
       	return ResponseEntity.status(HttpStatus.CREATED)
       			.body(new ResponseDto(AssestManagementConstants.STATUS_201, AssestManagementConstants.MESSAGE_201));
   	}
       
}



















//	EmployeeData createEmployee = employeeService.createUser(employeeData);
//employeeService.createEmployee(employeeData);
//return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AssestManagementConstants.STATUS_201, AssestManagementConstants.MESSAGE_201));
//if (createEmployee != null) {
//	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AssestManagementConstants.STATUS_201, AssestManagementConstants.MESSAGE_201));
//	
//} else {
//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(AssestManagementConstants.STATUS_400, AssestManagementConstants.MESSAGE_400));
//}
