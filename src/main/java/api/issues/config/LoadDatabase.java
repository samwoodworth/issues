package api.issues.config;

import api.issues.model.Issue;
import api.issues.repo.IssueRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(IssueRepo repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Issue("Issue 1", "Sam Woodworth")));
      log.info("Preloading " + repository.save(new Issue("Issue 2", "Sam Woodworth")));
    };
  }
}