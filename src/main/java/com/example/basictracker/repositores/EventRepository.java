package com.example.basictracker.repositores;

import com.example.basictracker.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserId(String userId);

    List<Event> findByActivityId(String activityId);

    List<Event> findByTimeStamp(LocalDateTime timestamp);

    Event findById(long id);
}
