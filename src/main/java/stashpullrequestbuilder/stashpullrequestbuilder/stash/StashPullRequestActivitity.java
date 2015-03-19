package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Nathan on 20/03/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StashPullRequestActivitity implements Comparable<StashPullRequestActivitity> {
    private StashPullRequestComment comment;

    public StashPullRequestComment getComment() {
        return comment;
    }

    public void setComment(StashPullRequestComment comment) {
        this.comment = comment;
    }

    public int compareTo(StashPullRequestActivitity target) {
        if (this.comment == null || target.getComment() == null) {
            return -1;
        }
        if (this.comment.getCommentId() > target.getComment().getCommentId()) {
            return 1;
        } else if (this.comment.getCommentId().equals(target.getComment().getCommentId())) {
            return 0;
        } else {
            return -1;
        }
    }
}
