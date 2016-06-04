package com.casaprestations.burs.attachement.config.postgres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "postgresEntityManagerFactory2", transactionManagerRef = "postgresTransactionManager2", basePackages = "com.casaprestations.burs.attachement.repository.calcul")
public class PostgresDataAccessConfig2 {

	@Autowired
	private Environment env;

	@Bean(name = "postgresDataSource2")
	@ConfigurationProperties(prefix = "datasource.dbcalcul")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabasePlatform(env.getProperty("spring.jpa.properties.hiberante.dialect"));
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		// Naming strategy
		// spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImpovedNamingStrategy
		return jpaVendorAdapter;

	}

	@Bean(name = "postgresEntityManagerFactory2")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource());
		lef.setJpaVendorAdapter(jpaVendorAdapter());
		lef.setPackagesToScan("com.casaprestations.burs.attachement.entity.db.calcul");
		//lef.setPersistenceUnitName("baseAttachement");
		lef.afterPropertiesSet();
		return lef.getObject();
	}

	@Bean(name = "postgresEntityManager2")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Bean(name = "postgresTransactionManager2")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
	
	@Bean
	 public JdbcTemplate jdbcTemplate() {
	  return new JdbcTemplate(dataSource());
	 }
}
