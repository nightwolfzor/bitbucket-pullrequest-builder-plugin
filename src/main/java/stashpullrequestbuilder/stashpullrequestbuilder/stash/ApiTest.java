package stashpullrequestbuilder.stashpullrequestbuilder.stash;

/**
 * Created by nathan on 20/03/2015.
 */
public class ApiTest {

    public static void main(String [] args) {
        StashApiClient api = new StashApiClient("cs-build", "b1gd4t4!build", "CHT", "checkout-bi");
        api.getPullRequests();
        api.getPullRequestComments("CHT","checkout-bi","6");
        api.postPullRequestComment("6","Test post comment");
    }

}
