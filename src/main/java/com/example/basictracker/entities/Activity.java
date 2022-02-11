package com.example.basictracker.entities;

import com.example.basictracker.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String activityName;

    public Activity(String activityName) {
        //this.id = Utils.generateRandomId(3);
        this.activityName = activityName;
    }

    public Activity() {

    }
}


