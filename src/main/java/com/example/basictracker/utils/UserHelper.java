package com.example.basictracker.utils;

import com.example.basictracker.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class UserHelper {
    @Getter
    @Setter
    public static List<User> listOfActiveUsers = new LinkedList<>();
}
