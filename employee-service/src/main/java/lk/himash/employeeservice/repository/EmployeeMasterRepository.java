package lk.himash.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.himash.employeeservice.model.EmployeeMaster;

@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {
	
    @Query("SELECT u FROM EmployeeMaster u WHERE u.Emp_Username = ?1")
    EmployeeMaster findByUserName(String name);
	
}
