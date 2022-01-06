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

    //http://localhost:8081/get_issues?user=admin
    @GetMapping("/get_issues")
    ResponseEntity<?> all(@RequestParam String user) {
        if (isAuthed()) {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    //http://localhost:8081/get_issue?id=1&user=admin
    @GetMapping("/get_issue")
    ResponseEntity<?> one(@RequestParam("id") Long id, @RequestParam("user") String user) {
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
    //http://localhost:8081/insert_issue?user=admin + Issue JSON
    @PostMapping("/insert_issue")
    ResponseEntity<?> insertIssue(@RequestBody Issue newIssue, @RequestParam String user) {
        if (isAuthed()) {
            return new ResponseEntity<>(repo.save(newIssue), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }    
    }
}