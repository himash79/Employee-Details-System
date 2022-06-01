package lk.himash.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMasterDto {

    private int Emp_ID;
    private String Emp_First_Name;
    private String Emp_Last_Name;
    private String Emp_Username;
    private String Emp_Password;
    private String Emp_Password_Enc;
    private String Emp_Email;

}
