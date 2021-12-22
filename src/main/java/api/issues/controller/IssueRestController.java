package api.issues.controller;

import java.util.List;
import api.issues.exceptions.IssueNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.issues.repo.IssueRepo;
import api.issues.model.Issue;


/*
String authenticated() {
    if is authenticated
        let through to api
    else forward to login
}
 */

@RestController
public class IssueRestController {

    private final IssueRepo repo;

    IssueRestController(IssueRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/get_issues")
    List<Issue> all() {
        System.out.println(repo.findById(1));
        return repo.findAll();
    }

    @GetMapping("/get_issue/{id}")
    Issue one(@PathVariable Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new IssueNotFoundException(id));
    }

    //Return issue or void?
    @PostMapping("/insert_issue")
    Issue insertIssue(@RequestBody Issue newIssue) {
        return repo.save(newIssue);
    }
}
