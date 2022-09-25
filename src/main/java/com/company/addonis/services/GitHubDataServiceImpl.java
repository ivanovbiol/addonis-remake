package com.company.addonis.services;

import com.company.addonis.config.GitHubConfig;
import com.company.addonis.models.Addon;
import com.company.addonis.models.GitHubData;
import com.company.addonis.repositories.GitHubRepository;
import com.company.addonis.services.contracts.GitHubDataService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GitHubDataServiceImpl implements GitHubDataService {

    public static final String ORIGIN_LINK_ERROR_MESSAGE = "Unable to connect GitHub." +
            "Please check if your origin link is valid.";
    public static final int GITHUB_DEFAULT_URL_LENGTH = 19;

    @Autowired
    private GitHubRepository gitHubRepository;
    @Autowired
    private GitHubConfig gitHubConfig;

    @Override
    public void addGitHubData(Addon addon) throws IllegalAccessException {
        GitHubData gitHubData = getDataFromGitHub(addon.getOriginLink(), null);
        GitHubData gitHubDataFromBase = gitHubRepository.save(gitHubData);
        addon.setGithubData(gitHubDataFromBase);
    }

    @Override
    public void updateGitHubData(Addon addon) throws IllegalAccessException {
        GitHubData gitHubData = getDataFromGitHub(addon.getOriginLink(), addon.getGithubData());
        gitHubRepository.save(gitHubData);
    }

    private GitHubData getDataFromGitHub(String originLink, GitHubData gitHubData) throws IllegalAccessException {
        if (gitHubData == null) {
            gitHubData = new GitHubData();
        }
        String gitHubOriginLink = originLink.substring(GITHUB_DEFAULT_URL_LENGTH);
        try {
            GHRepository repository = gitHubConfig.gitHubTokenLink().getRepository(gitHubOriginLink);
            gitHubData.setLastCommitTittle(repository.listCommits().toList().get(0).getCommitShortInfo().getMessage());
            gitHubData.setOpenIssuesCount(repository.getOpenIssueCount());
            gitHubData.setPullRequestsCount(repository.getPullRequests(GHIssueState.OPEN).size());
            gitHubData.setLastCommitDate(repository.listCommits().toList().get(0).getCommitShortInfo().getCommitDate());
            return gitHubData;
        } catch (IOException e) {
            throw new IllegalAccessException(ORIGIN_LINK_ERROR_MESSAGE);
        }
    }
}
