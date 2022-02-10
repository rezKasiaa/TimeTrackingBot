package com.example.basictracker;

import com.example.basictracker.entities.*;
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

        User agata = UserHelper.getListOfActiveUsers().get(0);

        var listOfEventIds = ActvitiesHelper.getBasicActivities().stream()
                .map(x -> new Event().generateEvent(agata.getUUID(), x.getId())).collect(Collectors.toList());

        System.out.println("Started events ids : " + listOfEventIds);
        System.out.println("Active events for user " + agata.getUserName() + " :" + Event.getActiveEventsByUserId(agata.getUUID()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event.finishEvent(listOfEventIds.get(0));
        System.out.println("Active events for user " + agata.getUserName() + " :" + Event.getActiveEventsByUserId(agata.getUUID()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event.getStatisticForAllActivities(agata.getUUID());
    }

    public static void insertData() {
        User agata = new User("Agata", Roles.ADMIN);
        UserHelper.setListOfActiveUsers(List.of(agata));

        ActvitiesHelper.setBasicActivities(Arrays.stream(BasicActivities.values())
                .map(x -> new Activity(x.toString()))
                .collect(Collectors.toList()));
    }
}
