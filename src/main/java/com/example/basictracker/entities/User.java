package com.example.basictracker.entities;

import com.example.basictracker.utils.Utils;
import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private String UUID;
    private String userName;
    private String role;

    public User(String userName, Roles role) {
        this.UUID = Utils.generateRandomId(32);
        this.userName = userName;
        this.role = role.toString();
    }
}
