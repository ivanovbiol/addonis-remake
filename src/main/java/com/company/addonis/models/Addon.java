package com.company.addonis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "addons")
public class Addon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ide_id")
    private IDE ide;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "addons_tags",
            joinColumns = @JoinColumn(name = "addon_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @Lob
    @Column(name = "image")
    @JsonIgnore
    private byte[] image;

    @Lob
    @Column(name = "binary_content")
    @JsonIgnore
    private byte[] binaryContent;

    @Column(name = "origin_link")
    private String originLink;

    @Column(name = "featured")
    private boolean featured;

    @OneToOne
    @JoinColumn(name = "github_data_id")
    private GitHubData githubData;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "download_count")
    private int downloadCount;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "total_voters")
    private int totalVoters;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @JsonIgnore
    @Transient
    private String imageString;

    @JsonIgnore
    @Transient
    private double calculatedScore;
}
