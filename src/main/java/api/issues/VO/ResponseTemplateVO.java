package api.issues.VO;

import api.issues.model.Issue;

public class ResponseTemplateVO {

    private Issue issue;
    private User user;

    public Issue getIssue() {
        return issue;
    }
    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
