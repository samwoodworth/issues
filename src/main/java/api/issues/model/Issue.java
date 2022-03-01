package api.issues.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Issue {

    @Id
    @GeneratedValue
    private Long id;
    private String issue;
    private String creatorName;

    public Issue() {}

    public Issue(Long id) {
        this.id = id;
    }

    public Issue(String issue, String creatorName) {
        this.issue = issue;
        this.creatorName = creatorName;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public boolean equals(Object o) {
  
        if (this == o)
            return true;
        if (!(o instanceof Issue))
            return false;
        Issue issue = (Issue) o;
        return Objects.equals(this.id, issue.id) && Objects.equals(this.issue, issue.issue) && Objects.equals(this.creatorName, issue.creatorName);
    }
  
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.issue, this.creatorName);
    }
  
    @Override
    public String toString() {
      return "Issue{" + "id=" + this.id + ", issue='" + this.issue + '\'' + ", creatorName='" + this.creatorName + '\'' + '}';
    }
}
