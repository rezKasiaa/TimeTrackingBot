package com.example.basictracker.entities;

import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
   // Event e;
    for (Event s : listOfActiveEvents) {
        if (eventId.equals(s.id)) {
            s.stopTime = LocalDateTime.now();
            s.isActive = false;
            listOfFinishedEvents.add(s);
            listOfActiveEvents.remove(s);
            System.out.println("Activity " + ActvitiesHelper.getActivityById(s.activityId).getActivityName()
                    + " successfully finished. Good job!");
            break;
        }
    }
  }
  public static List<String> getActiveEventsByUserId(String userId) {
    return listOfActiveEvents.stream().filter(x -> userId.equals(x.userId)).map(x -> ActvitiesHelper.getActivityById(x.activityId).getActivityName()).collect(Collectors.toList());
  }

  public static void getStatisticForAllActivities(String userId) {
      System.out.println("Finished activities : ");
      listOfFinishedEvents.stream().filter(x -> userId.equals(x.userId)).forEach(x -> {
          try {
              System.out.println(showStatistics(x));
          } catch (ParseException e) {
              e.printStackTrace();
          }
      });
      System.out.println("Active activities : ");
      listOfActiveEvents.stream().filter(x -> userId.equals(x.userId)).forEach(x -> {
          try {
              System.out.println(showStatistics(x));
          } catch (ParseException e) {
              e.printStackTrace();
          }
      });
  }

  private static String showStatistics(Event e) throws ParseException {
    //TODO change date format
      if (e.isActive) {
          return String.format("Activity type : %s \n Started at :  " + e.startTime
                          + " \n Total time : " + LocalDateTime.now().minusMinutes(e.startTime.getMinute()),
                  ActvitiesHelper.getActivityById(e.getActivityId()).getActivityName());
      } else {
          return String.format("Activity type : %s \n Started at :  " + e.startTime + "\n Finished at : " + e.stopTime
                          + " \n Total time : " + e.stopTime.minusMinutes(e.startTime.getMinute()),
                  ActvitiesHelper.getActivityById(e.getActivityId()).getActivityName());
      }
   }
}
