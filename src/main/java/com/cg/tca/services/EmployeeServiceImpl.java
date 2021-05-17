package com.cg.tca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tca.entities.Employee;
import com.cg.tca.exception.ResourceNotFoundException;
import com.cg.tca.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	//private SupervisorService supService;
	
	/**public Employee createEmployee(Employee employee) throws ResourceNotFoundException {
		Supervisor supervisor=supService.getSupervisorById(employee.getSupervisor().getSupervisorId());
		supervisor.getEmps().add(employee);
		supervisor.updateSupervisor(supervisor.getSupervisorId(),supervisor);
		return employeeRepository.save(employee);
	}**/
	
	@Override
	public Employee updateEmployee(Integer employeeId, Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employee.setEmployeeName(employeeDetails.getEmployeeName());
		employee.setEmployeeRole(employeeDetails.getEmployeeRole());
		employee.setEmployeeEmail(employeeDetails.getEmployeeEmail());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setPassword (employeeDetails.getPassword());
		employee.setUserId(employeeDetails.getUserId());
		final Employee updatedEmployee = employeeRepository.save(employee);
		
		return updatedEmployee;
	}
	

	public boolean deleteEmployeeById(Integer employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employeeRepository.delete(employee);
		
		return true;
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmpById(int empId) throws ResourceNotFoundException {
		Employee emp=employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empId));
		
		return emp;
	}
	@Override
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	
	}
	

}
