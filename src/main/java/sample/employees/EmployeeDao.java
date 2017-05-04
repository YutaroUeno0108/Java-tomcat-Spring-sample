package sample.employees;

import java.util.List;
import java.util.Map;

/**
 * Created by yutaroueno on 2017/05/04.
 */
public interface EmployeeDao {
    public void createEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
    public Employee getEmployeeById(int id);
    public List<Employee> getEmployeeByName(String name);
    public List<Employee> getEmployees();
}
