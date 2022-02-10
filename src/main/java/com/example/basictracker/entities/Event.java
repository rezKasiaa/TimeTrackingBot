package com.example.basictracker.entities;

import com.example.basictracker.utils.ActvitiesHelper;
import com.example.basictracker.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        if (e.isActive) {
            return String.format("Activity type : %s \n Started at :  " + formatDateTime(e.startTime)
                            + " \n Total time : " + findTotalTime(e.startTime),
                    ActvitiesHelper.getActivityById(e.getActivityId()).getActivityName());
        } else {
            return String.format("Activity type : %s \n Started at :  " + formatDateTime(e.startTime) + "\n Finished at : " + formatDateTime(e.stopTime)
                            + " \n Total time : " + findTotalTime(e.startTime, e.stopTime),
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
