package api.issues.controller;

import api.issues.exceptions.IssueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.issues.repo.IssueRepo;
import api.issues.model.Issue;

@RestController
public class IssueController {

    private final IssueRepo repo;

    //Repo constructor
    IssueController(IssueRepo repo) {
        this.repo = repo;
    }

    

    boolean isAuthed() {
        boolean authorized = true;
        if (authorized)
            return true;
        else
            return false;
    }

    //http://localhost:8081/get_issues
    @GetMapping("/get_issues")
    ResponseEntity<?> all(@RequestParam String user) {
        System.out.println("Get issues: " + user);
        if (isAuthed()) {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    //http://localhost:8081/get_issue?id=1
    @GetMapping("/get_issue")
    ResponseEntity<?> one(@RequestParam Long id) {
        //If not found return badRequest http status
        if (isAuthed()) {
            Issue foundIssue =  repo.findById(id)
                    .orElseThrow(() -> new IssueNotFoundException(id));
            return new ResponseEntity<>(foundIssue, HttpStatus.OK);
        } else {

            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    //Return issue or void?
    //http://localhost:8081/insert_issue + Issue JSON
    @PostMapping("/insert_issue")
    ResponseEntity<?> insertIssue(@RequestBody Issue newIssue) {
        if (isAuthed()) {
            return new ResponseEntity<>(repo.save(newIssue), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }    
    }
}

/*
    RestController in security to get loggedin status
    If true then allow through, if false redirect to login service
*/