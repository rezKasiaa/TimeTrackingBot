package com.example.basictracker.entities;

import com.example.basictracker.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity {
    private String id;
    private String activityName;

    public Activity(String activityName) {
        this.id = Utils.generateRandomId(3);
        this.activityName = activityName;
    }
}


