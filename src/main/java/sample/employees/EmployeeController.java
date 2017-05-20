package sample.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by yutaroueno on 2017/05/09.
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee-spring", method = GET)
    public String getAllEmployees(Model model){
        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm",employeeForm);
        model.addAttribute("employeeList",employeeService.getEmployees());
        return "list-employees-spring";
    }

    @RequestMapping(value = "/employee-spring", method = GET, params = {"employeeName","searchAction"})
    public String searchEmployeesByName(Model model, @RequestParam String searchAction, @RequestParam String employeeName) {
        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm", employeeForm);
        model.addAttribute("employeeList",employeeService.getEmployeeByName(employeeName));
        return "list-employees-spring";
    }

    @RequestMapping(value = "/employee-spring", method = GET,  params = {"idEmployee","searchAction"})
    public String searchEmployeesById(Model model, @RequestParam String idEmployee, @RequestParam String searchAction) {
        try {
            Employee employee = employeeService.getEmployeeById(Integer.valueOf(idEmployee));
            employee.setAction("edit");
            employee.setIdEmployee(idEmployee);
            model.addAttribute("employee",employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "new-employee-spring";
    }

    @RequestMapping(value = "/new-employee-spring", method = GET)
    public String transferNewEmployee(Model model) {
        Employee employee = new Employee();
        employee.setAction("add");
        model.addAttribute("employee", employee);
        return "new-employee-spring";
    }

    @RequestMapping(value = "/employee-spring", method = POST)
    public String removeEmployee(EmployeeForm employeeForm, Model model) {
        String action = employeeForm.getAction();
        switch (action) {
            case "remove":
                employeeService.deleteEmployee(Integer.valueOf(employeeForm.getIdEmployee()));
                break;
        }
        return this.getAllEmployees(model);
    }

    @RequestMapping(value = "/employee-spring-edit", method = POST)
    public String editEmployee(Employee employee, Model model) {
        String action = employee.getAction();
        switch (action) {
            case "add":
                employeeService.createEmployee(employee);
                break;
            case "edit":
                employee.setId(Integer.valueOf(employee.getIdEmployee()));
                employeeService.updateEmployee(employee);
                break;
        }
        return this.getAllEmployees(model);
    }

}