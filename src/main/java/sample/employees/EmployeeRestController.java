package sample.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by yutaroueno on 2017/05/14.
 */
@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(path = "employee-rest", method = GET)
    public List<Employee> getAllEmployees(Model model){
        return employeeService.getAllEmployees();
    }

}
