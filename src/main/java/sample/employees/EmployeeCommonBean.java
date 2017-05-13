package sample.employees;

import java.io.Serializable;

/**
 * Created by yutaroueno on 2017/05/13.
 */
public class EmployeeCommonBean implements Serializable {
    private static final long serialVersionUID = -4199707909716463701L;
    private String idEmployee;
    private String action;

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
