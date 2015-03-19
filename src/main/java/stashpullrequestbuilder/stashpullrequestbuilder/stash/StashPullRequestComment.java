package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Nathan McCarthy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StashPullRequestComment implements Comparable<StashPullRequestComment> {

    private Integer commentId;//
    private String text;


    @JsonProperty("id")
    public Integer getCommentId() {
        return commentId;
    }

    @JsonProperty("id")
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int compareTo(StashPullRequestComment target) {
        if (this.getCommentId() > target.getCommentId()) {
            return 1;
        } else if (this.getCommentId().equals(target.getCommentId())) {
            return 0;
        } else {
            return -1;
        }
    }
}
