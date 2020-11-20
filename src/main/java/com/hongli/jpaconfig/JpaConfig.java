package com.hongli.jpaconfig;

import com.hongli.service.BseOrderService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @Configuration : 正常配合@Bean 的 生成Bean 的实现 的配置注解
 * @EnableJpaRepositories : 会扫描扩展自 Sping Data JPA Repository的接口，给他们生成实现
 *                        : @EnableJpaRepositories(basePackageClasses = {BseOrderRepository.class})
 *                        : Or @EnableJpaRepositories(basePackages = "com.hongli.repository")
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.hongli.repository")
public class JpaConfig {

    /**
     * BasicDataSource 需要配合 DBCP 依赖
     * https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2
     * @return
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://www.curvelife.cn:3306/springcore?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);

        return dataSource;
    }

    /**
     * 数据库接口类型
     * @return
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }

    /**
     * LocalContainerEntityManagerFactoryBean 会创建 EntityManagerFactory
     * @param dataSource
     * @param jpaVendorAdapter
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPersistenceUnitName("BseOrder");
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.hongli.domain");
        return emf;
    }

    @Configuration
    @EnableTransactionManagement
    public static class TransactionConfig {
        @Autowired
        private EntityManagerFactory emf;

        @Bean
        public PlatformTransactionManager transactionManager() {

            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(emf);
            return transactionManager;
        }
    }

    @Bean
    public BseOrderService bseOrderService() {
        return new BseOrderService();
    }
}
