package com.company.addonis.services.contracts;

import com.company.addonis.models.Addon;
import com.company.addonis.models.GitHubData;

public interface GitHubDataService {

    void addGitHubData(Addon addon) throws IllegalAccessException;

    void updateGitHubData(Addon addon) throws IllegalAccessException;
}
