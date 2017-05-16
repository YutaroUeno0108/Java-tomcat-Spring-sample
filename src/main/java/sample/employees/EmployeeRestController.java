package sample.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by yutaroueno on 2017/05/14.
 */
@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(path = "employee-rest", method = GET)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping(path = "employee-rest", method = POST)
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return employeeService.getAllEmployees();
    }

    @RequestMapping(path = "employee-rest", method = PUT)
    public List<Employee> editEmployee(@RequestBody Employee employee) {
        employee.setId(Integer.valueOf(employee.getIdEmployee()));
        employeeService.updateEmployee(employee);
        return employeeService.getAllEmployees();
    }

}
