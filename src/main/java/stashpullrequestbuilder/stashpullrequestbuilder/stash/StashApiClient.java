package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.model.Jenkins;
import hudson.ProxyConfiguration;

/**
 * Created by Nathan McCarthy
 */
public class StashApiClient {
    private static final Logger logger = Logger.getLogger(StashApiClient.class.getName());

    private static final String STASH_HOST = "https://git.int.quantium.com.au";
    private static final String V1_API_BASE_URL = STASH_HOST + "/rest/api/1.0/projects/";
    private static final String V2_API_BASE_URL = STASH_HOST + "/rest/api/1.0/projects/";

    private String project;
    private String repositoryName;
    private Credentials credentials;

    public StashApiClient(String username, String password, String project, String repositoryName) {
        this.credentials = new UsernamePasswordCredentials(username, password);
        this.project = project;
        this.repositoryName = repositoryName;
    }

    public List<StashPullRequestResponseValue> getPullRequests() {
        String response = getRequest(V2_API_BASE_URL + this.project + "/repos/" + this.repositoryName + "/pull-requests/");
        try {
            return parsePullRequestJson(response).getPrValues();
        } catch(Exception e) {
            logger.log(Level.WARNING, "invalid pull request response.", e);
        }
        return Collections.EMPTY_LIST;
    }

    public List<StashPullRequestComment> getPullRequestComments(String commentOwnerName, String commentRepositoryName, String pullRequestId) {
        String response = getRequest(
            V1_API_BASE_URL + commentOwnerName + "/" + commentRepositoryName + "/pull-requests/" + pullRequestId + "/activities");
        try {
            return parseCommentJson(response);
        } catch(Exception e) {
            logger.log(Level.WARNING, "invalid pull request response.", e);
        }
        return Collections.EMPTY_LIST;
    }

    public void deletePullRequestComment(String pullRequestId, String commentId) {
        String path = V1_API_BASE_URL + this.project + "/" + this.repositoryName + "/pull-requests/" + pullRequestId + "/comments/" + commentId;
        //https://bitbucket.org/api/1.0/repositories/{accountname}/{repo_slug}/pullrequests/{pull_request_id}/comments/{comment_id}
        deleteRequest(path);
    }


    public StashPullRequestComment postPullRequestComment(String pullRequestId, String comment) {
        String path = V1_API_BASE_URL + this.project + "/" + this.repositoryName + "/pull-requests/" + pullRequestId + "/comments";
        try {
            NameValuePair content = new NameValuePair("text", comment);
            String response = postRequest(path, new NameValuePair[]{ content });
            return parseSingleCommentJson(response);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpClient getHttpClient() {
        HttpClient client = new HttpClient();
        if (Jenkins.getInstance() != null) {
            ProxyConfiguration proxy = Jenkins.getInstance().proxy;
            if (proxy != null) {
                logger.info("Jenkins proxy: " + proxy.name + ":" + proxy.port);
                client.getHostConfiguration().setProxy(proxy.name, proxy.port);
                String username = proxy.getUserName();
                String password = proxy.getPassword();
                // Consider it to be passed if username specified. Sufficient?
                if (username != null && !"".equals(username.trim())) {
                    logger.info("Using proxy authentication (user=" + username + ")");
                    client.getState().setProxyCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));
                }
            }
        }
        return client;
    }

    private String getRequest(String path) {
        HttpClient client = getHttpClient();
        client.getState().setCredentials(AuthScope.ANY, credentials);
        GetMethod httpget = new GetMethod(path);
        client.getParams().setAuthenticationPreemptive(true);
        String response = null;
        try {
            client.executeMethod(httpget);
            response = httpget.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void deleteRequest(String path) {
        HttpClient client = getHttpClient();
        client.getState().setCredentials(AuthScope.ANY, credentials);
        DeleteMethod httppost = new DeleteMethod(path);
        client.getParams().setAuthenticationPreemptive(true);
        String response = "";
        try {
            client.executeMethod(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String postRequest(String path, NameValuePair[] params) throws UnsupportedEncodingException {
        HttpClient client = getHttpClient();
        client.getState().setCredentials(AuthScope.ANY, credentials);
        PostMethod httppost = new PostMethod(path);
        httppost.setRequestBody(params);
        client.getParams().setAuthenticationPreemptive(true);
        String response = "";
        try {
            client.executeMethod(httppost);
            response = httppost.getResponseBodyAsString();
            logger.info("API Request Response: " + response);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    private StashPullRequestResponse parsePullRequestJson(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StashPullRequestResponse parsedResponse;
        parsedResponse = mapper.readValue(response, StashPullRequestResponse.class);
        return parsedResponse;
    }

    private List<StashPullRequestComment> parseCommentJson(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<BitBucketPullRequestActivitity> parsedResponse;
        parsedResponse = mapper.readValue(
                response,
                new TypeReference<List<BitBucketPullRequestActivitity>>() {
                });
        List<StashPullRequestComment> comments = new ArrayList<StashPullRequestComment>();
        for (BitBucketPullRequestActivitity a : parsedResponse) {
            comments.add(a.getComment());
        }
        return comments;
    }

    private StashPullRequestComment parseSingleCommentJson(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StashPullRequestComment parsedResponse;
        parsedResponse = mapper.readValue(
                response,
                StashPullRequestComment.class);
        return parsedResponse;
    }
}

