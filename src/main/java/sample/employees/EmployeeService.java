package sample.employees;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
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

    static public final String DATE_PATTERN ="dd-MM-YYYY";
    public JdbcTemplate jdbcTemplate;
    public void setup() {
        DataSourceConfiguration db = new DataSourceConfiguration();
        this.jdbcTemplate = db.jdbcTemplate();
    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    List<Employee> employeeList = EmployeeList.getInstance();

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public List<Employee> searchEmployeesByName(String name) {
        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getName)
                .thenComparing(Employee::getLastName);
        List<Employee> result = employeeList
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name) || e.getLastName().equalsIgnoreCase(name))
                .sorted(groupByComparator)
                .collect(Collectors.toList());

        return result;
    }

    public Employee getEmployee(long id) throws Exception {
        this.setup();
        Optional<Employee> match
                = employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from employees");
        for (Map employee:list){
            if (((Integer)employee.get("id")).longValue() == (id - 100)) {
                Employee resEmp = new Employee(
                        (String)employee.get("name"),
                        (String)employee.get("lastname"),
                        new SimpleDateFormat(DATE_PATTERN).format(employee.get("birthdate")),
                        (String)employee.get("role"),
                        (String)employee.get("department"),
                        (String)employee.get("email"),
                        ((Integer)employee.get("id")).longValue()
                );
                return resEmp;
            }
        }

        throw new Exception("The Employee id " + id + " not found");

    }

    public long addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee.getId();
    }

    public boolean updateEmployee(Employee customer) {
        int matchIdx = 0;
        Optional<Employee> match = employeeList.stream()
                .filter(c -> c.getId() == customer.getId())
                .findFirst();
        if (match.isPresent()) {
            matchIdx = employeeList.indexOf(match.get());
            employeeList.set(matchIdx, customer);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteEmployee(long id) {
        Predicate<Employee> employee = e -> e.getId() == id;
        if (employeeList.removeIf(employee)) {
            return true;
        } else {
            return false;
        }
    }
}
