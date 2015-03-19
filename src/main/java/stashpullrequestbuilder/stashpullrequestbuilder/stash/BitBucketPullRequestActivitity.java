package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Nathan on 20/03/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketPullRequestActivitity  {
    private StashPullRequestComment comment;

    public StashPullRequestComment getComment() {
        return comment;
    }

    public void setComment(StashPullRequestComment comment) {
        this.comment = comment;
    }
}
