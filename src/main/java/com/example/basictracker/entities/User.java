package com.example.basictracker.entities;

import com.example.basictracker.utils.Utils;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long UUID;
    private String userName;
    private String role;

    public User(String userName, Roles role) {
        //this.UUID = Utils.generateRandomId(32);
        this.userName = userName;
        this.role = role.toString();
    }

    public User() {

    }
}
