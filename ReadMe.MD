基于注解的方式 配置Spring Data JPA (Spring5 + Spring Data JPA)

1 - 依赖包说明：见POM 文件

2 - 配置 - 详细见 JpaConfig类

    1）DataSource: 使用 DBCP 的 BasicDataSource
    2）EntityManagerFactory: 使用 LocalContainerEntityManagerFactoryBean 生成
        注入DataSource
        设置JPA实现厂家
        设置扫描加载管理Entity 的 包路径
    3）TransactionManager: 
        @EnableTransactionManagement
        注入EntitiManagerFactory
    4）注入Service
    
3 - 坑
    1) @Transactional 注解使用在  @Component, @Controller 或 @Service的类上，不然事务失效
       体验：@Transactional 只是夹在@Test 方法上，导致事务不提交
    2) 配置TransactionManager 是必要条件
    
        
4 - 测试运行环境
    1) DB类型: Mysql
    2) SchemaName: springcore
    3) Table: Bse_order
    4) Column: id(int), order_num(String)
    