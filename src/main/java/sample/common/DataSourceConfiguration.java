package sample.common;

/**
 * Created by yutaroueno on 2017/05/03.
 */
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfiguration {

    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/sample");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }

    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
