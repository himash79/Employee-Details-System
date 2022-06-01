package lk.himash.employeeservice.DtoToModel;

import lk.himash.employeeservice.dto.EmployeeMasterDto;
import lk.himash.employeeservice.model.EmployeeMaster;

public class DtoConvertor {

    public static EmployeeMaster toEmpMaEntity(EmployeeMasterDto empMaDto) {
        EmployeeMaster empMa = new EmployeeMaster();
        empMa.setEmp_ID(empMaDto.getEmp_ID());
        empMa.setEmp_First_Name(empMaDto.getEmp_First_Name());
        empMa.setEmp_Last_Name(empMaDto.getEmp_Last_Name());
        empMa.setEmp_Username(empMaDto.getEmp_Username());
        empMa.setEmp_Password(empMaDto.getEmp_Password());
        empMa.setEmp_Password_Enc(empMaDto.getEmp_Password_Enc());
        empMa.setEmp_Email(empMaDto.getEmp_Email());
        return empMa;
    }
}
