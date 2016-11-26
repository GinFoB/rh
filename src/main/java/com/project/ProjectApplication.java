package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.project")
@EnableJpaRepositories
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}



	/*@Bean
	CommandLineRunner init() {
		return (args) -> {
			FileSystemUtils.deleteRecursively(new File(FileUploadController.ROOT));
			Files.createDirectory(Paths.get(FileUploadController.ROOT));
		};
	}*/


}
