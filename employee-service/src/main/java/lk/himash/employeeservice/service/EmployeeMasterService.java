package lk.himash.employeeservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lk.himash.employeeservice.model.EmployeeMaster;
import lk.himash.employeeservice.repository.EmployeeMasterRepository;

@Service
@Transactional
public class EmployeeMasterService {

    @Autowired
    private EmployeeMasterRepository empMasterRepo;

    public ResponseEntity<?> saveEmp(EmployeeMaster empMa) {
        BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
        EmployeeMaster savedUser;
        if(empMa.getEmp_Password_Enc() == null || empMa.getEmp_Password_Enc().isEmpty()) {
            String enc_password = bCryptPasswordEncoder.encode(empMa.getEmp_Password());
            empMa.setEmp_Password_Enc(enc_password);
            savedUser = empMasterRepo.save(empMa);
            System.out.println("When user doesn't have encrypt password");
            System.out.println(empMa);
        } else {
            String enc_password = bCryptPasswordEncoder.encode(empMa.getEmp_Password_Enc());
            empMa.setEmp_Password_Enc(enc_password);
            savedUser = empMasterRepo.save(empMa);
            System.out.println("When user have encrypt password");
            System.out.println(empMa);
        }
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getEmployees() {
        List<EmployeeMaster> emps = empMasterRepo.findAll();
        return new ResponseEntity(emps, HttpStatus.OK);
    }

    public ResponseEntity<?> getEmployeeDetails(int empID) {
        System.out.println("Start | getEmployeeDetails() method | EmployeeMasterService.class|");
        System.out.println("Parameter --> empID : " + empID);
        Optional<EmployeeMaster> emp = Optional.of(new EmployeeMaster());
        try {
             emp = empMasterRepo.findById(empID);
        }catch(Exception ex) {
            System.out.println("Exception found on | getEmployeeDetails() method | EmployeeMasterService.class|");
            System.out.println(ex.getMessage());
            return new ResponseEntity(emp, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(emp, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteEmployee(int empID) {
        System.out.println("Start | deleteEmployee() method | EmployeeMasterService.class|");
        System.out.println("Parameter --> empID : " + empID);
        try {
            empMasterRepo.deleteById(empID);
        }catch (Exception ex) {
            System.out.println("Exception found on | getEmployeeDetails() method | EmployeeMasterService.class|");
            System.out.println(ex.getMessage());
            return new ResponseEntity(empID, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Delete successfully with employee id " + empID, HttpStatus.OK);
    }
    
    public ResponseEntity<?> updateEmployee(EmployeeMaster newEmployee, String empID) {
    	EmployeeMaster oldEmployee = empMasterRepo.findById(Integer.parseInt(empID)).get();
    	EmployeeMaster updatedEmployee = settleUpdateDetails(oldEmployee, newEmployee);
    	System.out.println("updated employee details : " + updatedEmployee);
        return new ResponseEntity(updatedEmployee, HttpStatus.OK);
    }

    private static EmployeeMaster settleUpdateDetails(EmployeeMaster oldEmployee, EmployeeMaster newEmployee) {
    	BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
    	if(!newEmployee.getEmp_Password().equalsIgnoreCase(oldEmployee.getEmp_Password())) {
    		System.out.println("when user password updated");
        	oldEmployee.setEmp_First_Name(newEmployee.getEmp_First_Name());
        	oldEmployee.setEmp_Last_Name(newEmployee.getEmp_Last_Name());
        	oldEmployee.setEmp_Username(newEmployee.getEmp_Username());
        	oldEmployee.setEmp_Password(newEmployee.getEmp_Password());
        	// when user updated the password then update encrypted password also
            String new_enc_password = bCryptPasswordEncoder.encode(newEmployee.getEmp_Password());
            oldEmployee.setEmp_Password_Enc(new_enc_password);
        	oldEmployee.setEmp_Email(newEmployee.getEmp_Email());
    	} else {
    		System.out.println("when user password not updated");
        	oldEmployee.setEmp_First_Name(newEmployee.getEmp_First_Name());
        	oldEmployee.setEmp_Last_Name(newEmployee.getEmp_Last_Name());
        	oldEmployee.setEmp_Username(newEmployee.getEmp_Username());
        	oldEmployee.setEmp_Password(newEmployee.getEmp_Password());
        	oldEmployee.setEmp_Password_Enc(newEmployee.getEmp_Password_Enc());
        	oldEmployee.setEmp_Email(newEmployee.getEmp_Email());
    	}
        return newEmployee;
    }

}
