package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {


    private final Environment env;

    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManager() {
        String PACKAGES_TO_SCAN = "web";

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("db.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("db.hbm2ddl.auto"));
        properties.put("hibernate.current_session_context_class", env.getProperty("db.current_session_context_class"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        entityManager.setDataSource(getDataSource());
        entityManager.setJpaProperties(properties);
        entityManager.setPackagesToScan(PACKAGES_TO_SCAN);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManager;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManager().getObject());
        return transactionManager;
    }
}