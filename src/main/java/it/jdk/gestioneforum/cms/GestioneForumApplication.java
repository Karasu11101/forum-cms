package it.jdk.gestioneforum.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource(
		ignoreResourceNotFound = false,
		value = "classpath:db_connection.properties"
)
@ComponentScan(basePackages = "it.jdk.gestioneforum.cms")
@EntityScan({"it.jdk.gestioneforum.cms.persistence"})
@EnableJpaRepositories({"it.jdk.gestioneforum.cms.repository"})
public class GestioneForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneForumApplication.class, args);
	}

}
