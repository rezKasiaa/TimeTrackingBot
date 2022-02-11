package com.example.basictracker;

import com.example.basictracker.entities.*;
import com.example.basictracker.repositores.UserRepository;
import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.EventHelper;
import com.example.basictracker.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class BasicTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTrackerApplication.class, args);
//        insertData();
//
//        User agata = UserHelper.getListOfActiveUsers().get(0);
//
//        var listOfEventIds = ActvitiesHelper.getBasicActivities().stream()
//                .map(x -> EventHelper.generateEvent(agata.getUUID(), x.getId())).collect(Collectors.toList());
//
//        System.out.println("Started events ids : " + listOfEventIds);
//        System.out.println("Active events for user " + agata.getUserName() + " :" + EventHelper.getActiveEventsByUserId(agata.getUUID()));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        EventHelper.finishEvent(listOfEventIds.get(0));
//        System.out.println("Active events for user " + agata.getUserName() + " :" + EventHelper.getActiveEventsByUserId(agata.getUUID()));
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        EventHelper.getStatisticForAllActivities(agata.getUUID());
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new User("Jack", Roles.USER));
            repository.save(new User("Chloe", Roles.USER));
            repository.save(new User("Kim", Roles.USER));
            repository.save(new User("David", Roles.USER));
            repository.save(new User("Michelle", Roles.USER));

            // fetch all users
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual user by ID
            Optional<User> user = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");
        };

//    public static void insertData() {
//        User agata = new User("Agata", Roles.ADMIN);
//        UserHelper.setListOfActiveUsers(List.of(agata));
//
//        ActvitiesHelper.setBasicActivities(Arrays.stream(BasicActivities.values())
//                .map(x -> new Activity(x.toString()))
//                .collect(Collectors.toList()));
//    }
}
}
