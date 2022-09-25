package com.company.addonis.repositories;

import com.company.addonis.models.GitHubData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubRepository extends JpaRepository<GitHubData, Integer> {
}
