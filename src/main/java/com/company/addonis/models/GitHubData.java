package com.company.addonis.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "github_data")
public class GitHubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "open_issues_count")
    private int openIssuesCount;

    @Column(name = "pulls_count")
    private int pullRequestsCount;

    @Column(name = "last_commit_date")
    private Date lastCommitDate;

    @Column(name = "last_commit_tittle")
    private String lastCommitTittle;
}

