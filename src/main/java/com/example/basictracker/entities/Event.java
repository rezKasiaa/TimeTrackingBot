package com.example.basictracker.entities;

import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private String id;
    private String userId;
    private String activityId;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private boolean isActive;
    private static List<Event> listOfActiveEvents = new LinkedList<>();
    private static List<Event> listOfFinishedEvents = new LinkedList<>();


public String generateEvent(String userId, String activityId) {
    this.id = Utils.generateRandomId(5);
    this.userId = userId;
    this.activityId = activityId;
    this.startTime = LocalDateTime.now();
    this.stopTime = null;
    this.isActive = true;

    listOfActiveEvents.add(this);
    System.out.println("Activity " + ActvitiesHelper.getActivityById(activityId).getActivityName()
            + " started!Keep going!");

    return id;
}

public static void finishEvent(String eventId) {
    for (Event s : listOfActiveEvents) {
        if (eventId.equals(s.id)) {
            s.stopTime = LocalDateTime.now();
            s.isActive = false;
            listOfFinishedEvents.add(s);
            listOfActiveEvents.remove(s);
            System.out.println("Activity " + ActvitiesHelper.getActivityById(s.activityId).getActivityName()
                    + " successfully finished. Good job!");
        }
    }
  }
}
