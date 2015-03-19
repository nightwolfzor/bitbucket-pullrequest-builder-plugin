package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by Nathan McCarthy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StashPullRequestActivityResponse {
    private List<StashPullRequestActivitity> prValues;

    private int size;//

    @JsonProperty("values")
    public List<StashPullRequestActivitity> getPrValues() {
        return prValues;
    }

    @JsonProperty("values")
    public void setPrValues(List<StashPullRequestActivitity> prValues) {
        this.prValues = prValues;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(int size) {
        this.size = size;
    }

}
