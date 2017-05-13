package sample.employees;


import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yutaroueno on 2017/05/04.
 */
@Repository
@Getter
@Setter
public class EmployeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao{
    /** controll transaction**/
    private PlatformTransactionManager transactionManager;
    /** date formatting**/
    SimpleDateFormat inputformat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat outputformat = new SimpleDateFormat("yyyyMMdd");
    static public final String DATE_PATTERN ="yyyyMMdd";

    @Override
    /** create new employee**/
    public void createEmployee(Employee employee){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql = "";
        Map<String, Object> paramMap;
        try {
            paramMap = new HashMap<String, Object>();
            paramMap.put("id", null);
            paramMap.put("name", employee.getName());
            paramMap.put("lastname", employee.getLastName());
            Date date = inputformat.parse(employee.getBirthDate(), new ParsePosition(0));
            String dateString = outputformat.format(date);
            paramMap.put("birthdate", dateString);
            paramMap.put("role", employee.getRole());
            paramMap.put("department", employee.getDepartment());
            paramMap.put("email", employee.getEmail());
            sql = "INSERT INTO employees values (:id,:name,:lastname,:birthdate,:role,:department,:email)";
            getNamedParameterJdbcTemplate().update(sql, paramMap);
            transactionManager.commit(status);
            System.out.println("TRANSACTION COMPLETED SUCCESSFULLY");
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }
    }

    @Override
    /** update employee**/
    public void updateEmployee(Employee employee) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql = "";
        Map<String, Object> paramMap;
        try {
            paramMap = new HashMap<String, Object>();
            paramMap.put("id", employee.getId());
            paramMap.put("name", employee.getName());
            paramMap.put("lastname", employee.getLastName());
            Date date = inputformat.parse(employee.getBirthDate(), new ParsePosition(0));
            String dateString = outputformat.format(date);
            paramMap.put("birthdate", dateString);
            paramMap.put("role", employee.getRole());
            paramMap.put("department", employee.getDepartment());
            paramMap.put("email", employee.getEmail());
            sql = "UPDATE employees SET name = :name, lastname = :lastname, birthdate = :birthdate,role = :role, department = :department, email = :email where id = :id";
            getNamedParameterJdbcTemplate().update(sql, paramMap);
            transactionManager.commit(status);
            System.out.println("TRANSACTION COMPLETED SUCCESSFULLY");
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }
    }

    @Override
    /** delete employee**/
    public void deleteEmployee(int id) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        String sql = "";
        Map<String, Object> paramMap;
        try {
            paramMap = new HashMap<String, Object>();
            paramMap.put("id", id);
            sql = "DELETE FROM employees where id = :id";
            getNamedParameterJdbcTemplate().update(sql, paramMap);
            transactionManager.commit(status);
            System.out.println("TRANSACTION COMPLETED SUCCESSFULLY");
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }
    }

    @Override
    /** find all employees **/
    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employees";
        EmployeeListResultSetExtractor extractor = new EmployeeListResultSetExtractor();
        List<Employee> result = getNamedParameterJdbcTemplate().query(sql,extractor);
        return result;
    }

    @Override
    /** find employees by name **/
    public List<Employee> getEmployeeByName(String name) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("name", "%" + name + "%");
        paramMap.put("lastname", "%" + name + "%");
        String sql = "SELECT * FROM employees WHERE name like :name or lastname like :lastname";
        EmployeeListResultSetExtractor extractor = new EmployeeListResultSetExtractor();
        List<Employee> result = getNamedParameterJdbcTemplate().query(sql,paramMap,extractor);
        return result;
    }

    @Override
    /** find employee by id(pk) **/
    public Employee getEmployeeById(int id) {
        Map<String, Integer> paramMap = new HashMap<String, Integer>();
        paramMap.put("id", id);
        String sql = "SELECT * FROM employees WHERE id=:id";
        Map<String,Object> result = getNamedParameterJdbcTemplate().queryForMap(
                sql, paramMap);
        if (result!= null) {
            Employee employee = new Employee(
                    (String)result.get("name"),
                    (String)result.get("lastname"),
                    new SimpleDateFormat(DATE_PATTERN).format(result.get("birthdate")),
                    (String)result.get("role"),
                    (String)result.get("department"),
                    (String)result.get("email"),
                    ((Integer)result.get("id")).longValue()
            );
            return employee;
        }
        return null;

    }
}
