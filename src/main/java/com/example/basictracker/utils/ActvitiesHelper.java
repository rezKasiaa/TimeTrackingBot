package com.example.basictracker.utils;

import com.example.basictracker.entities.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ActvitiesHelper {
    @Getter
    @Setter
    protected static List<Activity> basicActivities = new LinkedList<>();

    private ActvitiesHelper() {
    }

    public static Activity getActivityById(String id) {
        Optional<Activity> activity = basicActivities.stream().filter(x -> id.equals(x.getId())).findAny();
        return activity.isPresent() ? activity.get() : null;
    }
}
