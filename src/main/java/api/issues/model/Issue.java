package api.issues.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Issue {

    @Id
    @GeneratedValue
    private Long id;
    private String issue;
    //Date created, creator

    Issue() {}

    public Issue(Long id, String issue) {
        this.id = id;
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    
}
