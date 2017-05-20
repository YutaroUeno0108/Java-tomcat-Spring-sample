package sample.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Employee getEmployeeById(@RequestParam String employeeId){
        return employeeService.getEmployeeById(Integer.valueOf(employeeId));
    }

    @RequestMapping(path = "employee-rest-name", method = GET)
    public List<Employee> getEmployeeByName(@RequestParam String employeeName){
        return employeeService.getEmployeeByName(employeeName);
    }

    @RequestMapping(path = "employee-rest", method = POST)
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }

    @RequestMapping(path = "employee-rest", method = PUT)
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(path = "employee-rest-del", method = PUT)
    public void deleteEmployee(@RequestBody String employeeId){
        employeeService.deleteEmployee(Integer.valueOf(employeeId));
    }

    @RequestMapping(path = "employee-rest-all", method = GET)
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

}
