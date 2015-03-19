package bitbucketpullrequestbuilder.bitbucketpullrequestbuilder.bitbucket;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Nathan on 20/03/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketPullRequestActivitity  {
    private BitbucketPullRequestComment comment;

    public BitbucketPullRequestComment getComment() {
        return comment;
    }

    public void setComment(BitbucketPullRequestComment comment) {
        this.comment = comment;
    }
}
