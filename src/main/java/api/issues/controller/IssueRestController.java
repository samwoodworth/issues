package api.issues.controller;

import java.util.List;
import java.util.stream.Collectors;

import api.issues.exceptions.IssueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //Repo constructor
    IssueRestController(IssueRepo repo) {
        this.repo = repo;
    }

    boolean isAuthed() {
        boolean authorized = true;
        if (authorized)
            return true;
        else
            return false;
    }

    //Change to @RequestParams?

    @GetMapping("/get_issues")
    ResponseEntity<?> all() {
        if (isAuthed()) {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

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
    @PostMapping("/insert_issue")
    ResponseEntity<?> insertIssue(@RequestBody Issue newIssue) {
        if (isAuthed()) {
            return new ResponseEntity<>(repo.save(newIssue), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }    }
}

/*
    RestController in security to get loggedin status
    If true then allow through, if false redirect to login service
*/