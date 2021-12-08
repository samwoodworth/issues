package api.issues.controller;

import java.util.List;
import java.util.Optional;

import api.issues.exceptions.IssueNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.issues.repo.IssueRepo;
import api.issues.model.Issue;


@RestController
public class IssueController {

    private final IssueRepo repo;

    IssueController(IssueRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/get_issues")
    List<Issue> all() {
        return repo.findAll();
    }

    @GetMapping("/get_issue/{id}")
    Optional<Issue> one(@PathVariable Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new IssueNotFoundException(id));
    }

    //Return issue or void?
    @PutMapping("/insert_issue")
    Issue insertIssue(@RequestBody Issue newIssue) {
        return repo.save(newIssue);
    }
}
