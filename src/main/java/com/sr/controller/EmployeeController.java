package com.sr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sr.model.Employee;
import com.sr.service.EmployeeDao;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {


	@Autowired
	public EmployeeDao empDao;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllEmployee()
	{
		List<Employee>empList=empDao.getAllEmployee();
		if(empList!=null) {
			return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Employee List is empty",HttpStatus.NO_CONTENT);
	}

	@PostMapping(value="/addEmp",consumes="application/json; character=utf-8")
	public ResponseEntity<?>addEmployee(@RequestBody Employee emp){
		if (empDao.addEmployee(emp)!=null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);

		}
		return new ResponseEntity<String>("sorry cannot enter the data",HttpStatus.CONFLICT);
	}

}
