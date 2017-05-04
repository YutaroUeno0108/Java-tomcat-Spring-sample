package sample.employees;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yutaroueno on 2017/05/04.
 */
public class EmployeeListResultSetExtractor implements ResultSetExtractor<List<Employee>> {
    /** date format**/
    static public final String DATE_PATTERN ="dd-MM-YYYY";
    @Override
    public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String,Employee> map = new LinkedHashMap<String,Employee>();
        Employee employee = null;
        while(rs.next()){
            String id = new Integer(rs.getInt("id")).toString();
            employee = map.get(rs.getInt("id"));
            if (employee == null){
                employee = new Employee(
                        (String)rs.getString("name"),
                        (String)rs.getString("lastname"),
                        (String)rs.getString("birthdate"),
                        (String)rs.getString("role"),
                        (String)rs.getString("department"),
                        (String)rs.getString("email"),
                        ((Integer)rs.getInt("id")).longValue());
                map.put(id,employee);
            }
        }
        return new ArrayList<Employee>(map.values());
    }
}
