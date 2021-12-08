package api.issues.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import api.issues.model.Issue;


public interface IssueRepo extends JpaRepository<Issue, Long> {
    
}
