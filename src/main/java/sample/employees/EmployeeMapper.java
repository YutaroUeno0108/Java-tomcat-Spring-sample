package sample.employees;

import java.util.List;

/**
 * Created by yutaroueno on 2017/05/18.
 */
public interface EmployeeMapper {
    void createEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
    List<Employee> getEmployees();
    Employee getEmployeeById(int id);
    List<Employee> getEmployeeByName(String name);
}
