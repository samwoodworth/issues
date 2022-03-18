package api.issues.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Issue {

    @Id
    @GeneratedValue
    private Long id;
    private String issueDesc;
    private String creatorName;

    public Issue() {}

    public Issue(Long id) {
        this.id = id;
    }

    public Issue(String issueDesc, String creatorName) {
        this.issueDesc = issueDesc;
        this.creatorName = creatorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", issueDesc='" + issueDesc + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
