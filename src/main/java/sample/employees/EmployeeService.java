package sample.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yutaroueno on 2017/05/18.
 */
@Transactional
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmployeeById(int id){
        return employeeMapper.getEmployeeById(id);
    }

    public List<Employee> getEmployeeByName (String employeeName) {
        return employeeMapper.getEmployeeByName(employeeName);
    }


    public void createEmployee(Employee employee) {
        employeeMapper.createEmployee(employee);
    }

    public void updateEmployee(Employee employee){
        employeeMapper.updateEmployee(employee);
    }

    public void deleteEmployee(int id){
        employeeMapper.deleteEmployee(id);
    }

    public List<Employee> getEmployees(){
        return employeeMapper.getEmployees();
    }
}
