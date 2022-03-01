package api.issues.controller;

import api.issues.exceptions.IssueNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import api.issues.repo.IssueRepo;
import api.issues.model.Issue;

@Controller
public class IssueController {

    @Autowired
    private final IssueRepo repo;

    IssueController(IssueRepo repo) {
        this.repo = repo;
    }

    //http://localhost:8081/get_issues?user=admin
    @GetMapping("/get_issues")
    ModelAndView all(@RequestParam String user) {
        return new ModelAndView("printIssues", "issues", repo.findAll());
    }

    //http://localhost:8081/get_issue?id=1&user=admin
    @GetMapping("/get_issue")
    ModelAndView one() {
        return new ModelAndView("getIssueForm", "issue", new Issue());
/*
        Issue foundIssue =  repo.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(id));
        return new ModelAndView("printIssue", "issue", foundIssue);*/
    }

    @PostMapping("/get_issue")
    ModelAndView one(@ModelAttribute Issue issue) {
        Issue foundIssue =  repo.findById(issue.getId())
                .orElseThrow(() -> new IssueNotFoundException(issue.getId()));
        return new ModelAndView("getIssueResult", "foundIssue", foundIssue);
    }

    //http://localhost:8081/insert_issue?user=admin + Issue JSON
    @PostMapping("/insert_issue")
    ResponseEntity<?> insertIssue(@RequestBody Issue newIssue, @RequestParam String user) {
        return new ResponseEntity<>(repo.save(newIssue), HttpStatus.OK);
    }

    @PostMapping("/add_many/{num}")
    ResponseEntity<?> insertN(@PathVariable("num") int num, @RequestParam String user) {

        List<Issue> issueList = new ArrayList<>();

        for (int n=0; n<num; n++) {
            long count = repo.count()+n+1;
            issueList.add(new Issue("Issue #"+count, "Creator #"+count));
        }
        return new ResponseEntity<>(repo.saveAll(issueList), HttpStatus.OK);
    }

    @PostMapping("/add_one")
    ResponseEntity<?> insertOne(@RequestParam String user) {
        long count = repo.count()+1;
        repo.save(new Issue("Issue #"+count, "Creator #"+count));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}