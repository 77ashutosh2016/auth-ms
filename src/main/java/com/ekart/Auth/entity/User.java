package com.ekart.Auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="app_user")
public class User {

    @GeneratedValue
    @Id
    private Long userId;

    @Column(unique = true)
    private String userName;

    private String userpassword;

    @ElementCollection
    @CollectionTable(name="app_user_roles", joinColumns = @JoinColumn(name="user_id"))
    @Column(name= "role")
    private List<String> userRoles;

}
