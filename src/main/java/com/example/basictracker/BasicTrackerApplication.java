package com.example.basictracker;

import com.example.basictracker.entities.Activity;
import com.example.basictracker.entities.Event;
import com.example.basictracker.entities.Roles;
import com.example.basictracker.entities.User;
import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.UserHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BasicTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTrackerApplication.class, args);
        insertData();

       var listOfEventIds = UserHelper.getListOfActiveUsers().stream().map(x -> new Event()
                .generateEvent(x.getUUID(), ActvitiesHelper.getBasicActivities().get(0).getId())).collect(Collectors.toList());

        System.out.println("Created event`s ids : " + listOfEventIds);

        listOfEventIds.stream().forEach(x -> Event.finishEvent(x));
    }

    public static void insertData() {
        User agata = new User("Agata", Roles.ADMIN);
        User kornej = new User("Kornej", Roles.ADMIN);
        UserHelper.setListOfActiveUsers(List.of(agata, kornej));

        String[] activities = {"sleep", "eat", "work", "play"};
        ActvitiesHelper.setBasicActivities(Arrays.stream(activities)
                .map(x -> new Activity(x))
                .collect(Collectors.toList()));
    }

}
