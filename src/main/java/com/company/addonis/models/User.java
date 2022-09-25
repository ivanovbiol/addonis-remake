package com.company.addonis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Lob
    @Column(name = "photo")
    @JsonIgnore
    private byte[] photo;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "admin")
    private boolean isAdmin;

    @JsonIgnore
    @Transient
    private String image;

    @JsonIgnore
    @Transient
    private int uploadedAddons;
}

