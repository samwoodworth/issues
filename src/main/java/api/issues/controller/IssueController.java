package api.issues.controller;

import api.issues.exceptions.IssueNotFoundException;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import api.issues.repo.IssueRepo;
import api.issues.model.Issue;
import api.issues.model.CreateMany;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IssueController {

    @Autowired
    private final IssueRepo repo;

    IssueController(IssueRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/signout")
    public ModelAndView signout(HttpServletRequest request) throws Exception{

        URLConnection con = new URL("http://localhost:8080/getAuth").openConnection();

        //Checking for JSESSIONID cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookie is: " + cookie.getName());
            if (cookie.getName().equals("JSESSIONID")) {
                cookie.setMaxAge(0);
                System.out.println("Cookie age: " + cookie.getMaxAge());
            }
        }

        return new ModelAndView("home");
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("home");
    }

    @GetMapping("/get_issues")
    public ModelAndView all() {
        return new ModelAndView("printIssues", "issues", repo.findAll());
    }

    @GetMapping("/get_issue")
    public ModelAndView one() {
        return new ModelAndView("getIssueForm", "issue", new Issue());
    }

    @PostMapping("/get_issue")
    public ModelAndView one(@ModelAttribute Issue issue) {
        Issue foundIssue =  repo.findById(issue.getId())
                .orElseThrow(() -> new IssueNotFoundException(issue.getId()));
        return new ModelAndView("getIssueResult", "foundIssue", foundIssue);
    }

    @GetMapping("/insert_issue")
    public ModelAndView insertIssue() {
        return new ModelAndView("insertIssueForm", "issue", new Issue());
    }

    //Check if already exists
    @PostMapping("/insert_issue")
    public ModelAndView insertIssue(@ModelAttribute Issue issue) {
        repo.save(issue);
        return new ModelAndView("insertIssueResult", "issue", issue);
    }

    @GetMapping("/add_many")
    public ModelAndView insertN() {
        return new ModelAndView("addManyForm", "createMany", new CreateMany());
    }

    @PostMapping("/add_many")
    public ModelAndView insertN(@ModelAttribute CreateMany createMany) {

        List<Issue> issueList = new ArrayList<>();

        for (int n=0; n<createMany.getNumToCreate(); n++) {
            long count = repo.count()+n+1;
            issueList.add(new Issue("Issue #"+count, "Creator #"+count));
        }
        repo.saveAll(issueList);
        return new ModelAndView("addManyResult", "numToCreate", createMany);
    }

    @GetMapping("/add_one")
    public ModelAndView insertOne() {
        long count = repo.count()+1;
        repo.save(new Issue("Issue #"+count, "Creator #"+count));
        return new ModelAndView("addOne", "count", repo.count());
    }
}