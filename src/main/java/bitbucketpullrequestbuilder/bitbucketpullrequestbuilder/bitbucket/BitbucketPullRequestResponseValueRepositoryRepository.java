package bitbucketpullrequestbuilder.bitbucketpullrequestbuilder.bitbucket;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketPullRequestResponseValueRepositoryRepository {
    private String slug;
    private BitbucketPullRequestResponseValueRepositoryProject project;

    @JsonProperty("project")
    public BitbucketPullRequestResponseValueRepositoryProject getRepository() {
        return project;
    }

    @JsonProperty("project")
    public void setRepository(BitbucketPullRequestResponseValueRepositoryProject project) {
        this.project = project;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProjectName() {
        if (this.project != null && project.getKey() != null) {
            return project.getKey();
        }
        return null;
    }

    public String getRepositoryName() {
        return this.slug;
    }
}

