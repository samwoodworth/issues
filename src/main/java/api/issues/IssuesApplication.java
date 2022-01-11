package api.issues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import api.issues.repo.IssueRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = IssueRepo.class)
public class IssuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuesApplication.class, args);
	}

}
