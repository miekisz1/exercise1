package others;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.*;
import selenium.framework.common.others.Log;

import java.io.IOException;
import java.util.*;

/**
 * Created by PLARMIE on 2017-10-01.
 */
public class GitHubApi {
    private GitHubClient client;
    private UserService userService;
    private CommitService commitService;
    private RepositoryService repositoryService;
    private DataService dataService;
    private PullRequestService pullRequestService;

    public GitHubApi(String gitHubAddress, String login, String password){
        client = new GitHubClient(gitHubAddress);
        client.setCredentials(login, password);
        userService = new UserService(client);
        commitService = new CommitService(client);
        repositoryService = new RepositoryService(client);
        dataService = new DataService(client);
        pullRequestService = new PullRequestService(client);

    }

    protected User getUser(final String login) throws IOException {
        try {
            User currentUser = userService.getUser(login);
            Log.message("User: '%s' exists", login);
            return currentUser;
        } catch (IOException e) {
            Log.message("Exception during get user: '%s', \n%s", login, e.getStackTrace());
            throw e;
        }
    }

    protected CommitUser getCommitUser(String login, String email){
        CommitUser commitUser = new CommitUser();
        commitUser.setName(login);
        commitUser.setDate(new Date());
        commitUser.setEmail(email);

        return commitUser;
    }

    protected CommitUser getCommitUser(String login){
        return getCommitUser(login, null);
    }

    public Repository createRepository(final String repositoryName) throws IOException {
        Repository repository = new Repository();
        repository.setName(repositoryName);
        repository.setCreatedAt(new Date());
        repository.setPushedAt(new Date());
        repository.setUpdatedAt(new Date());
        repository.setMasterBranch("master");
        repository.setHasIssues(true);
        repository.setSource(null);

        Repository createdRepository;
        try {
            createdRepository= repositoryService.createRepository(repository);
            Log.message("Repository: '%s' created", repositoryName);
        } catch (IOException e) {
            Log.message("Exception during create repository: '%s', \n%s", repository, e.getStackTrace());
            throw e;
        }
        return createdRepository;
    }

    public List<Repository> getAllRepositories(String user) throws IOException {
        List<Repository> repositories = new ArrayList<Repository>();
        try {
            repositories = repositoryService.getRepositories(user);
            Log.message("Get repositories for user: '%s' - done", user);
        } catch (IOException e) {
            Log.message("Exception during get repositories for user: '%s', \n%s", user, e.getStackTrace());
            throw e;
        }
        return repositories;
    }

    public Repository getRepository(String user, String repositoryName) throws IOException {
        Repository repository = null;
        try {
            repository = repositoryService.getRepository(user, repositoryName);
            Log.message("Get repository: '%s' for user: '%s' - done", repositoryName, user);
        } catch (IOException e) {
            Log.message("Exception during get repository: '%s' for user: '%s', \n%s", repositoryName, user, e.getStackTrace());
            throw e;
        }
        return repository;
    }

    public void deleteRepository(String user, String repositoryName) throws IOException {

        try {
            client.delete(String.format("/repos/%s/%s", user, repositoryName));
            Log.message("Repository: '%s' for user: '%s' deleted", repositoryName, user);
        } catch (IOException e) {
            Log.message("Exception during delete repository: '%s' for user: '%s', \n%s", repositoryName, user, e.getStackTrace());
            throw e;
        }
    }

    public void deleteAllRepositories(String user) throws IOException {
        List<Repository> repositories = getAllRepositories(user);
        for (Repository repository : repositories) {
            deleteRepository(user, repository.getName());
        }
    }

    public Commit pushCommit(String user, String repositoryName, String commitMessage, String fileName, String fileContent) throws IOException {
        RepositoryId repositoryId = new RepositoryId(user, repositoryName);
        Repository repository = getRepository(user, repositoryName);
        Tree baseTree = dataService.getTree(repositoryId, "master");

        Commit commit = new Commit();
        commit.setMessage(commitMessage);
        commit.setAuthor(getCommitUser(user, "artur.miekus@gmail.com"));
        commit.setCommitter(getCommitUser(user, "artur.miekus@gmail.com"));
        commit.setTree(createTree(repositoryId, fileName, fileContent, baseTree.getSha()));
        Commit newCommit=null;
        try {
            newCommit = dataService.createCommit(repository, commit);
            Log.message("Commit '%s' pushed succesfull", commitMessage);
        } catch (IOException e) {
            Log.message("Exception during push commit: '%s', \n%s", commitMessage, e.getStackTrace());
            throw e;
        }
        return newCommit;
    }

    private String createBlob(String content, RepositoryId repositoryId) throws IOException {
        Blob blob = new Blob();
        blob.setContent(content);
        blob.setEncoding("UTF-8");
        String blobSsh = null;
        try {
            blobSsh = dataService.createBlob(repositoryId, blob);
            Log.message("Blob created");
        } catch (IOException e) {
            Log.message("Blob not created, \n%s", e.getStackTrace());
            throw e;
        }
        return blobSsh;
    }

    private TreeEntry createBlobTreeEntry(String path, String content, RepositoryId repositoryId) throws IOException {
        TreeEntry treeEntry = new TreeEntry();
        treeEntry.setPath(path);
        treeEntry.setMode(TreeEntry.MODE_BLOB);
        treeEntry.setSha(createBlob(content, repositoryId));
        treeEntry.setType(TreeEntry.TYPE_BLOB);

        return treeEntry;
    }

    private Tree createTree(RepositoryId repositoryId, String fileName, String fileContent, String baseTreeSha) throws IOException {
        List<TreeEntry> treeEntryList = new ArrayList<TreeEntry>();
        treeEntryList.add(createBlobTreeEntry(fileName,fileContent, repositoryId));
        Tree tree;
        try {
            if(null == baseTreeSha)
                tree = dataService.createTree(repositoryId, treeEntryList);
            else
                tree = dataService.createTree(repositoryId, treeEntryList, baseTreeSha);
            Log.message("Tree created");
        } catch (IOException e) {
            Log.message("Tree not created, \n%s", e.getStackTrace());
            throw e;
        }
        return tree;
    }

}
