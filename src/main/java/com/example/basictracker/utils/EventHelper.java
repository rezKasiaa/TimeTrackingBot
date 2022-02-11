package com.example.basictracker.utils;

import com.example.basictracker.entities.Activity;
import com.example.basictracker.entities.Event;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
public class EventHelper {
    private static Map<Event, Activity> startedActivities = new HashMap<>();
    private static Map<Event, LocalDateTime> activityDuration = new HashMap<>();


    public static String generateEvent(String userId, String activityId) {
        var event = Event.builder()
                .id(Utils.generateRandomId(5))
                .activityId(activityId)
                .userId(userId)
                .timeStamp(LocalDateTime.now())
                .build();

        startedActivities.put(event, ActvitiesHelper.getActivityById(activityId));
        System.out.println("Activity " + ActvitiesHelper.getActivityById(activityId).getActivityName()
                + " started!Keep going!");

        return event.getId();
    }

    public static void finishEvent(String eventId) {
        for (Event s : startedActivities.keySet()) {
            if (eventId.equals(s.getId())) {
                LocalDateTime duration = LocalDateTime.now().minusMinutes(s.getTimeStamp().getMinute());
                s.setTimeStamp(duration);
                activityDuration.put(s, duration);
                startedActivities.remove(s);
                System.out.println("Activity " + ActvitiesHelper.getActivityById(s.getActivityId()).getActivityName()
                        + " successfully finished. Good job!");
                break;
            }
        }
    }

    public static List<String> getActiveEventsByUserId(String userId) {
       return startedActivities.keySet().stream()
                .filter(x -> userId.equals(x.getUserId()))
                .map(x -> ActvitiesHelper.getActivityById(x.getActivityId()).getActivityName())
                .collect(Collectors.toList());
    }

    public static void getStatisticForAllActivities(String userId) {
        System.out.println("Finished activities : ");
        activityDuration.keySet().stream().filter(x -> userId.equals(x.getUserId())).forEach(x -> {
            try {
                System.out.println(showStatistics(x));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Active activities : ");
        startedActivities.keySet().stream().filter(x -> userId.equals(x.getUserId())).forEach(x -> {
            try {
                System.out.println(showStatistics(x));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private static String showStatistics(Event e) throws ParseException {
        if (startedActivities.containsValue(e)) {
            return String.format("Activity type : %s \n Started at :  " + formatDateTime(e.getTimeStamp())
                            + " \n Total time : " + findTotalTime(e.getTimeStamp()),
                    ActvitiesHelper.getActivityById(e.getActivityId()).getActivityName());
        } else {
            return String.format("Activity type : %s \n  Total time : " + formatDateTime(e.getTimeStamp()),
                    ActvitiesHelper.getActivityById(e.getActivityId()).getActivityName());
        }
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private static String findTotalTime(LocalDateTime startTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(startTime);

        long years = tempDateTime.until(currentTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(currentTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(currentTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(currentTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(currentTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(currentTime, ChronoUnit.SECONDS);

        return (years + "-" +
                months + "-" +
                days + " " +
                hours + ":" +
                minutes + ":" +
                seconds);
    }

    private static String findTotalTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime tempDateTime = LocalDateTime.from(startTime);

        long years = tempDateTime.until(endTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(endTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(endTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(endTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(endTime, ChronoUnit.SECONDS);

        return (years + "-" +
                months + "-" +
                days + " " +
                hours + ":" +
                minutes + ":" +
                seconds);
    }
}
