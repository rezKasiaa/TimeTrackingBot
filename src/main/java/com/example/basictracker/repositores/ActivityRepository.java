package com.example.basictracker.repositores;

import com.example.basictracker.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByActivityName(String activityName);

    Activity findById(long id);
}
