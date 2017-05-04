package sample.employees;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sample.common.DataSourceConfiguration;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yutaroueno on 2017/04/29.
 */
public class EmployeeService {

    private static ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    List<Employee> employeeList = EmployeeList.getInstance();

    public List<Employee> getAllEmployees() {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        return empDao.getEmployees();
    }

    public List<Employee> searchEmployeesByName(String name) {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        return empDao.getEmployeeByName(name);
    }

    public Employee getEmployee(long id) throws Exception {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        Employee result = empDao.getEmployeeById((int)id);
        if (result != null) return result;
        throw new Exception("The Employee id " + id + " not found");

    }

    public long addEmployee(Employee employee) {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        empDao.createEmployee(employee);
        return employee.getId();
    }

    public boolean updateEmployee(Employee employee) {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        empDao.updateEmployee(employee);
        return true;
    }

    public boolean deleteEmployee(long id) {
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        empDao.deleteEmployee((int)id);
        return true;
    }
}
