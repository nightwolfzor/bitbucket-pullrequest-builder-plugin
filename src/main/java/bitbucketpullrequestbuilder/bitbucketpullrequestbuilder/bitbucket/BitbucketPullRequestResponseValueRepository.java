package bitbucketpullrequestbuilder.bitbucketpullrequestbuilder.bitbucket;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Nathan McCarthy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketPullRequestResponseValueRepository {
    private BitbucketPullRequestResponseValueRepositoryRepository repository;

    @JsonIgnore
    private BitbucketPullRequestResponseValueRepositoryBranch branch;

    @JsonIgnore
    private BitbucketPullRequestResponseValueRepositoryCommit commit;

    private String latestChangeset;
    private String id;


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) { //TODO
        this.id = id;
        this.branch = new BitbucketPullRequestResponseValueRepositoryBranch();
        this.branch.setName(id);
    }

    @JsonProperty("latestChangeset")
    public String getLatestChangeset() {
        return latestChangeset;
    }

    @JsonProperty("latestChangeset")
    public void setLatestChangeset(String latestChangeset) { //TODO
        this.latestChangeset = latestChangeset;
        this.commit = new BitbucketPullRequestResponseValueRepositoryCommit();
        this.commit.setHash(latestChangeset);
    }

    @JsonProperty("repository")
    public BitbucketPullRequestResponseValueRepositoryRepository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(BitbucketPullRequestResponseValueRepositoryRepository repository) {
        this.repository = repository;
    }

    @JsonProperty("branch")
    public BitbucketPullRequestResponseValueRepositoryBranch getBranch() {
        return branch;
    }

    @JsonProperty("branch")
    public void setBranch(BitbucketPullRequestResponseValueRepositoryBranch branch) {
        this.branch = branch;
    }

    public BitbucketPullRequestResponseValueRepositoryCommit getCommit() {
        return commit;
    }

    public void setCommit(BitbucketPullRequestResponseValueRepositoryCommit commit) {
        this.commit = commit;
    }
}


