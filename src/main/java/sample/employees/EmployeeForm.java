package sample.employees;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yutaroueno on 2017/05/10.
 */
public class EmployeeForm extends EmployeeCommonBean implements Serializable {

    private static final long serialVersionUID = 3608224748701288000L;

    private List<Employee> employeeList;


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
