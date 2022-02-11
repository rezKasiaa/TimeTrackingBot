package com.example.basictracker;

import com.example.basictracker.entities.*;
import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.EventHelper;
import com.example.basictracker.utils.UserHelper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class BasicTrackerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void activityMustBeActiveForUser() {
        User agata = new User("Agata", Roles.ADMIN);
        User kornej = new User("Kornej", Roles.ADMIN);
        UserHelper.setListOfActiveUsers(List.of(agata, kornej));

        ActvitiesHelper.setBasicActivities(Arrays.stream(BasicActivities.values())
                .map(x -> new Activity(x.toString()))
                .collect(Collectors.toList()));

        var listOfEventIds = UserHelper.getListOfActiveUsers().stream()
                .map(x -> EventHelper.generateEvent(x.getUUID(), ActvitiesHelper.getBasicActivities().get(0).getId())).collect(Collectors.toList());

       var activityForAgata = EventHelper.getActiveEventsByUserId(agata.getUUID());
       Assertions.assertEquals(activityForAgata.size(), 1);
       Assertions.assertEquals(activityForAgata.get(0), ActvitiesHelper.getBasicActivities().get(0).getActivityName());

        var activityForKornej = EventHelper.getActiveEventsByUserId(kornej.getUUID());
        Assertions.assertEquals(activityForKornej.size(), 1);
        Assertions.assertEquals(activityForKornej.get(0), ActvitiesHelper.getBasicActivities().get(0).getActivityName());

        listOfEventIds.stream().forEach(x -> EventHelper.finishEvent(x));

        activityForAgata = EventHelper.getActiveEventsByUserId(agata.getUUID());
        Assertions.assertTrue(activityForAgata.isEmpty());

        activityForKornej = EventHelper.getActiveEventsByUserId(kornej.getUUID());
        Assertions.assertTrue(activityForKornej.isEmpty());
    }
}
