
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import others.GitHubApi;
import page.objects.MainPage;
import page.objects.WelcomePage;
import page.objects.repository.RepositoryNavigationPage;
import selenium.framework.common.browser.Browser;
import selenium.framework.common.exceptions.SeleniumFrameworkException;
import selenium.framework.common.tests.TestCommon;
import selenium.framework.common.tests.TestParameters;

import java.io.IOException;

public class GitHubTests extends TestCommon {

    private String url = "api.github.com";
    private String login = "login";
    private String password = "password";
    private GitHubApi api;
    private String repositoryName = "sample_repository_name";
    private String branchName = "TestBranchName";

    @BeforeSuite
    public void beforSuite(){
        api=new GitHubApi(url, login, password);
    }

    @Test(dataProvider = "testParameters")
    public void creatingRepository(final TestParameters parameters) throws SeleniumFrameworkException {
        new Browser(parameters).openUrl("https://www.github.com", MainPage.class)
            .clickSignInLink()
                .setLogin(login)
                .setPassword(password)
                .clickSigInButton()
                    .clickStartProjectButton()
                        .setRepositoryName(repositoryName)
                        .selectInitializeThisRepositoryCheckBox(true)
                        .clickCreateRepositoryButton(RepositoryNavigationPage.class)
                .closeBrowser();
    }

    @Test(dataProvider = "testParameters")
    public void pushCommit(final TestParameters parameters) throws SeleniumFrameworkException {
        new Browser(parameters).openUrl("https://www.github.com", MainPage.class)
            .clickSignInLink()
                .setLogin(login)
                .setPassword(password)
                .clickSigInButton()
                    .setRepositoryFilter(repositoryName)
                    .clickRepository(repositoryName)
                        .clickCreateNewFileButton()
                            .setFileName("Test file")
                            .selectNewBranchRadioButton()
                            .setBranchName(branchName)
                            .clickCommitNewFielButton(MainPage.class)
                .closeBrowser();
    }

    @Test(dataProvider = "testParameters")
    public void createPullRequest(final TestParameters parameters) throws SeleniumFrameworkException {
        new Browser(parameters).openUrl("https://www.github.com", MainPage.class)
            .clickSignInLink()
                .setLogin(login)
                .setPassword(password)
                .clickSigInButton()
                    .setRepositoryFilter(repositoryName)
                    .clickRepository(repositoryName, RepositoryNavigationPage.class)
                        .clickPullRequestsMenuItem()
                            .clickNewPullRequestButton()
                            .selectBranchToCompae(branchName)
                            .clickCreatePullRequestButton()
        .closeBrowser();
    }

    @Test(dataProvider = "testParameters")
    public void deletingRepository(final TestParameters parameters) throws SeleniumFrameworkException {
        new Browser(parameters).openUrl("https://www.github.com", MainPage.class)
            .clickSignInLink()
                .setLogin(login)
                .setPassword(password)
                .clickSigInButton()
                    .setRepositoryFilter(repositoryName)
                    .clickRepository(repositoryName, RepositoryNavigationPage.class)
                        .clickSettingsMenuItem()
                            .clickDeleteThisRepositoryButton()
                            .setRepositoryName(repositoryName)
                            .clickIUnderstandTheConsequencesDeleteThisRepositoryButton(WelcomePage.class)
         .closeBrowser();
    }

    @Test
    public void DeletingAllRepositories() throws IOException {
        api.deleteAllRepositories(login);
    }
}
