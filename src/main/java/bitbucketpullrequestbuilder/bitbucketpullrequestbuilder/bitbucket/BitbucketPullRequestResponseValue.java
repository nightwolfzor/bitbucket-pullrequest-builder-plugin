package bitbucketpullrequestbuilder.bitbucketpullrequestbuilder.bitbucket;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Nathan McCarthy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketPullRequestResponseValue {
    private String description; //
    private Boolean locked; //

    private String title; //

    private BitbucketPullRequestResponseValueRepository toRef;

    private Boolean closed; //

    private BitbucketPullRequestResponseValueRepository fromRef;

    private String state; //
    private String createdDate; //
    private String updatedDate; //

    private String id; //

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("locked")
    public Boolean getLocked() {
        return locked;
    }

    @JsonProperty("locked")
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BitbucketPullRequestResponseValueRepository getToRef() {
        return toRef;
    }

    public void setToRef(BitbucketPullRequestResponseValueRepository toRef) {
        this.toRef = toRef;
    }

    @JsonProperty("closed")
    public Boolean getClosed() {
        return closed;
    }

    @JsonProperty("closed")
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public BitbucketPullRequestResponseValueRepository getFromRef() {
        return fromRef;
    }

    public void setFromRef(BitbucketPullRequestResponseValueRepository fromRef) {
        this.fromRef = fromRef;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("updatedDate")
    public String getUpdatedDate() {
        return updatedDate;
    }

    @JsonProperty("updatedDate")
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
