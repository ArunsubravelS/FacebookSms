package com.ass.sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MySqlConfig {

	@Bean
	public HikariDataSource getDataSource() {
	
		String password =System.getenv("MYSQL_PASSWORD");
		String userName =System.getenv("MYSQL_USERNAME");
		String portName =System.getenv("PORT");
		System.out.println("password :"+password);
		System.out.println("userName :"+userName);
		System.out.println("portName :"+portName);
		
		HikariConfig hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/operational_db");
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("root");
		
		
		//hikariConfig.setConnectionTimeout(250);

		return new HikariDataSource(hikariConfig);
			
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(HikariDataSource hikariDataSource){
		return new JdbcTemplate(hikariDataSource);
	}
}
